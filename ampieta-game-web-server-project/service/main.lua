local skynet=require "skynet"

skynet.start(function ()
    ---写入启动日志
    skynet.error("skynet server start")
end)