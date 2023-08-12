package com.github.sunzy.remoting.transport.netty.codec;

import com.github.sunzy.enums.CompressTypeEnum;
import com.github.sunzy.extension.ExtensionLoader;
import com.github.sunzy.remoting.constants.RpcConstants;
import com.github.sunzy.remoting.dto.RpcMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class RpcMessageDecoder extends LengthFieldBasedFrameDecoder {

    public RpcMessageDecoder() {
        this(RpcConstants.MAX_FRAME_LENGTH, 5, 4, -9, 0);
    }

    /**
     * @param maxFrameLength      Maximum frame length. It decide the maximum length of data that can be received.
     *                            If it exceeds, the data will be discarded.
     * @param lengthFieldOffset   Length field offset. The length field is the one that skips the specified length of byte.
     * @param lengthFieldLength   The number of bytes in the length field.
     * @param lengthAdjustment    The compensation value to add to the value of the length field
     * @param initialBytesToStrip Number of bytes skipped.
     *                            If you need to receive all of the header+body data, this value is 0
     *                            if you only want to receive the body data, then you need to skip the number of bytes consumed by the header.
     */
    public RpcMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,
                             int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        Object decoded = super.decode(ctx, in);
        if(decoded instanceof ByteBuf) {
            ByteBuf frame = (ByteBuf) decoded;
            if(frame.readableBytes() >= RpcConstants.TOTAL_LENGTH) {
                try {
                    return decodeFrame(frame);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return super.decode(ctx, in);
    }

    private Object decodeFrame(ByteBuf in) {
        checkMagic(in);
        checkVersion(in);
        int fullLength = in.readInt();
        // build RpcMessage object
        byte messageType = in.readByte();
        byte codecType = in.readByte();
        byte compressType = in.readByte();
        int requestId = in.readInt();
        RpcMessage rpcMessage = RpcMessage.builder()
                .codec(codecType)
                .requestId(requestId)
                .messageType(messageType).build();
        if (messageType == RpcConstants.HEARTBEAT_REQUEST_TYPE) {
            rpcMessage.setData(RpcConstants.PING);
            return rpcMessage;
        }
        if (messageType == RpcConstants.HEARTBEAT_RESPONSE_TYPE) {
            rpcMessage.setData(RpcConstants.PONG);
            return rpcMessage;
        }

        if (messageType == RpcConstants.REQUEST_TYPE) {
            rpcMessage.setData(in.readBytes(fullLength - RpcConstants.TOTAL_LENGTH));
            return rpcMessage;
        }
        int bodyLength = fullLength - RpcConstants.HEAD_LENGTH;
        if(bodyLength > 0) {
            byte[] bs = new byte[bodyLength];
            in.readBytes(bs);
            // decompress the bytes
//            String compressName = CompressTypeEnum.getName(compressType);
//            ExtensionLoader.getExtensionLoader(Compress.class)
        }

        return null;
    }

    private void checkVersion(ByteBuf in) {
        byte version = in.readByte();
        if(version!= RpcConstants.VERSION) {
            throw new RuntimeException("version isn't compatible");
        }
    }


    private void checkMagic(ByteBuf in) {
        int len = RpcConstants.MAGIC_NUMBER.length;
        byte[] tmp = new byte[len];
        in.readBytes(tmp);
        for (int i = 0; i < len; i++) {
            if(tmp[i] != RpcConstants.MAGIC_NUMBER[i]) {
                throw new IllegalArgumentException("Unknown magic code: " + Arrays.toString(tmp));
            }
        }

    }
}

