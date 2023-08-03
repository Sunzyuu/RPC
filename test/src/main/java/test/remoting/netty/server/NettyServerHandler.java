package test.remoting.netty.server;

import test.remoting.netty.dto.RpcRequest;
import test.remoting.netty.dto.RpcResponse;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 这段代码是Netty服务器的处理器类，主要负责处理接收到的客户端请求，并返回一个固定的响应消息。
 * 在接收到客户端消息后，它会将消息内容打印出来，并通过自增计数记录处理次数，然后向客户端返回一个固定的响应消息。
 * 同时，当服务器发生异常时，会及时进行处理并关闭连接。
 * @author sunzy
 * @date 2023/8/2 18:54
 */
@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    private static final AtomicInteger atomicInteger = new AtomicInteger(1);

    /**
     * 这是Netty服务器处理器的核心方法，当服务器接收到客户端发送的消息时，会调用该方法进行处理。
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            RpcRequest rpcRequest = (RpcRequest) msg;
            log.info("server receive msg: [{}] ,times:[{}]", rpcRequest, atomicInteger.getAndIncrement());
            RpcResponse messageFromServer = RpcResponse.builder().message("message from server").build();
            ChannelFuture f = ctx.writeAndFlush(messageFromServer);
            f.addListener(ChannelFutureListener.CLOSE);
        } finally {
            ReferenceCountUtil.release(msg);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("server catch exception ", cause);
        ctx.close();
    }
}
