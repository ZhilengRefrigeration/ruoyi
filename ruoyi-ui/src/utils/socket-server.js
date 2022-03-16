import { Message } from "element-ui";
import store from '@/store'
// let wsUrl = 'ws://127.0.0.1:9002/warning/api';

export default class SocketService {
  /**
   * 单例
   */
  static instance = null;
  static get Instance() {
    if (!this.instance) {
      this.instance = new SocketService();
    }
    return this.instance;
  }

  // 和服务端连接的socket对象
  ws = null;

  // 存储回调函数
  callBackMapping = {};

  // 标识是否连接成功
  connected = false;

  // 记录重试的次数
  sendRetryCount = 0;

  // 重新连接尝试的次数
  connectRetryCount = 0;

  // 是否手动关闭websocket连接
  isManualClose = false

  //  定义连接服务器的方法
  connect() {
    // 连接服务器
    if (!window.WebSocket) {
      return console.log("您的浏览器不支持WebSocket");
    }
    //网关转发
    let wsUrl = 'ws://localhost:8080/warning/webSocket/warning/api';
    wsUrl += `/${store.getters.name}`
    this.ws = new WebSocket(wsUrl)

    // 连接成功的事件
    this.ws.onopen = () => {
      this.connected = true;
      // 重置重新连接的次数
      this.connectRetryCount = 0;
    };
    // 1.连接服务端失败
    // 2.当连接成功之后, 服务器关闭的情况
    this.ws.onclose = (event) => {
      // e.code === 1000  表示正常关闭。 无论为何目的而创建, 该链接都已成功完成任务。
      // e.code !== 1000  表示非正常关闭。
      console.log("onclose event: ", event);
      this.connected = false;
      if (event && event.code !== 1000) {
        console.log("连接服务端失败");
        // 如果不是手动关闭，这里的重连会执行；如果调用了手动关闭函数，这里重连不会执行
        this.connectRetryCount++;
        setTimeout(() => {
          this.connect();
        }, 500 * this.connectRetryCount);
      }
    };
    // 得到服务端发送过来的数据
    this.ws.onmessage = (msg) => {
      // 真正服务端发送过来的原始数据时在msg中的data字段
      const recvData = JSON.parse(msg.data);
      console.log(recvData)
      const socketType = recvData.socketType;
      if (this.callBackMapping[socketType]){
        this.callBackMapping[socketType].call(this, recvData);
      }
    };
  }

  // 回调函数的注册
  registerCallBack(socketType, callBack) {
    this.callBackMapping[socketType] = callBack;
  }

  // 取消某一个回调函数
  unRegisterCallBack(socketType) {
    this.callBackMapping[socketType] = null;
  }


  // 发送数据的方法
  send(data) {
    // 判断此时此刻有没有连接成功
    if (this.connected) {
      this.sendRetryCount = 0;
      this.ws.send(JSON.stringify(data));
      console.log('xxxxxxxxxxxx')
    } else if (!this.isManualClose) {
      console.log('=============')
      this.sendRetryCount++;
      setTimeout(() => {
        this.send(data);
      }, this.sendRetryCount * 500);
    }
  }

  // 手动关闭websocket （这里手动关闭会执行onclose事件）
  closeWebsocket() {
    if (this.ws) {
      try {
        this.ws.close(); // 关闭websocket
        this.ws = null
        this.callBackMapping = {}
        this.connected = false
        this.sendRetryCount = 0
        this.connectRetryCount = 0
        this.isManualClose = true
        this.instance = null
        SocketService.instance = null
      } catch (error) {
        console.log(error)
      }
    }
  }
}

const MessageEvent = { // onmessage回调函数的event
  data: { // websocket通讯接收到的数据
    socketType: '', // websocket双方通讯的名称
    data: {}, // 返回的真实数据
  }
}
