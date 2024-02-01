## 启动运行

```bash
# 进入项目目录
cd ruoyi-ui

# 安装依赖
# 建议不要直接使用 cnpm 安装依赖，会有各种诡异的 bug。可以通过如下操作解决 npm 下载速度慢的问题
npm install --registry=https://registry.npmmirror.com

# 启动服务
npm run dev
```

## 项目访问

http://localhost:10001  
端口号修改： vite.config.ts 中的 server.port 字段，点此查看 [vite 配置项](https://cn.vitejs.dev/config/)

## 发布

```bash
# 构建生产环境
npm run build:prod

# 构建测试环境
npm run build:stage
```

