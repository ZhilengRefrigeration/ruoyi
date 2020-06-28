package com.sarnath.modbusserver.dtuutils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.InetSocketAddress;

public class DTUServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(DTUServerHandler.class);

    private byte[] cBuf = new byte[0];

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        InetSocketAddress ipSocket = (InetSocketAddress )ctx.channel().remoteAddress();
        String addr = ipSocket.getAddress().getHostAddress();
        int port = ipSocket.getPort();
        LOG.info("通道读取，对方位于" + addr + ":" + port);
        ByteBuf buffer = (ByteBuf)msg;
        if (buffer != null)
        {
            //string msg = BitConverter.ToString(buffer.Array);
            //LogUtil.Info(msg);
            // 读取长度
            int len = buffer.readableBytes();
            byte[] curBuf = new byte[len];
            buffer.readBytes(curBuf,0,len);
            //System.arraycopy(buffer.array(), buffer.arrayOffset(), curBuf, 0, len);
            String tempMsg = BitConverter.toHexString(curBuf);
            LOG.info("xxxxxxxxx:"+tempMsg);
            //
            Unpacking(curBuf);
        }
    }

    /// <summary>
    /// 递归处理当前包
    /// </summary>
    /// <param name="curBuf"></param>
    private void Unpacking(byte[] curBuf)
    {
        // 读取长度
        int len = curBuf.length;
        // 定义拼接数据包
        byte[] revBuf = new byte[len + cBuf.length];
        // 拼接 缓存数据包
        System.arraycopy(cBuf, 0, revBuf, 0, cBuf.length);
        // 拼接 新接收数据包
        System.arraycopy(curBuf, 0, revBuf, cBuf.length, len);
        // 使用完重置缓存包
        cBuf = new byte[0];
        // 包长判断
        if (len >= 4)
        {
            String tempMsg = BitConverter.toHexString(revBuf);
            LOG.info("xxxxxxxxx:"+tempMsg);

            String tempMsg2 = BitConverter.toHexString(BitConverter.copyFrom(revBuf, 2, 2));
            LOG.info("xxxxxxxxx2:"+tempMsg2);
            LOG.info("xxxxxxxxx2:"+BitConverter.toHexString(BitConverter.getBytes((short) 6)));
            //2 3 位为整包长度
            short packageLen = BitConverter.toShort(BitConverter.copyFrom(revBuf, 2, 2));
            LOG.info("packageLen："+packageLen);
            if (packageLen==0)
            {
                return;
            }
            if (packageLen > revBuf.length)
            {
                // 缓存断包  等待下一个数据包
                LOG.info("缓存断包!");
                cBuf = revBuf;
            }
            else
            {
                // 根据长度 拆包
                byte[] comBuf = new byte[packageLen];
                System.arraycopy(revBuf, 0, comBuf, 0, packageLen);
                // 业务处理
                DoSomeThing(comBuf);
                //// 重置缓存包
                //cBuf = new byte[0];
                int remLen = revBuf.length - packageLen;
                if (remLen>0)
                {
                    LOG.info("粘包处理!");
                    // 重置当前处理包
                    curBuf = new byte[remLen];
                    System.arraycopy(revBuf, packageLen, curBuf, 0, remLen);
                    // 递归处理剩余数据包
                    Unpacking(curBuf);
                }
            }
        }
        else
        {
            // 缓存断包 等待下一个数据包
            LOG.info("缓存断包!");
            cBuf = revBuf;
        }
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

    private void DoSomeThing(byte[] comBuf)
    {
        String  msg = BitConverter.toHexString(comBuf);
        LOG.info("DoSomeThing:"+msg);

        //int dataLen = BitConverter.ToInt16(comBuf, 2) - 14;
        //byte[] DataByte = new byte[dataLen];
        //Array.ConstrainedCopy(comBuf, 14, DataByte, 0, dataLen);

        //// 0 1 头
        //// 2 3 长度
        //// 4 5 厂站号
        //int stationId = BitConverter.ToInt16(comBuf, 4);
        //// 6 7 通道号
        //int channelId = BitConverter.ToInt16(comBuf, 6);
        //int startAddress = BitConverter.ToInt16(comBuf, 11);
        //int type = Convert.ToInt16(comBuf[9]);
        //LogUtil.Info("场站号：" + stationId.ToString() + ", 通道号：" + channelId.ToString() + ", 起始地址：" + startAddress+",type："+ type);
        //LogUtil.Info(stationId+":"+string.Join("-", GetRaw(DataByte)));
    }
}
