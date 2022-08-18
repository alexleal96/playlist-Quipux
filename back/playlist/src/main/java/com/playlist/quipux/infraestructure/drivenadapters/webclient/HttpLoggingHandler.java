package com.playlist.quipux.infraestructure.drivenadapters.webclient;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.MDC;

import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class HttpLoggingHandler extends LoggingHandler {

    public HttpLoggingHandler() {
        super(LogLevel.INFO);
    }

    @Override
    protected String format(ChannelHandlerContext ctx, String event, Object arg) {
        if (arg instanceof ByteBuf) {
            ByteBuf msg = (ByteBuf) arg;
            return msg.toString(StandardCharsets.UTF_8)
                    .replaceAll("Authorization: .*", "Authorization: [hidden value]")
                    .replaceAll("x-token: .*", "x-token: [hidden value]");
        }
        return super.format(ctx, event, arg);
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        ctx.fireChannelRegistered();
    }

    @Override
    public void connect(
            ChannelHandlerContext ctx,
            SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) {
        ctx.connect(remoteAddress, localAddress, promise);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.fireChannelActive();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        ctx.fireUserEventTriggered(evt);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        ctx.fireChannelInactive();
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
        ctx.fireChannelUnregistered();
    }

    @Override
    public void flush(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.fireChannelReadComplete();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (logger.isEnabled(internalLevel)) {
            logger.log(internalLevel, format(ctx, "READ", msg));
        }
        ctx.fireChannelRead(msg);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        if (logger.isEnabled(internalLevel)) {
            String message = format(ctx, "WRITE", msg);

            var matcher = Pattern.compile("correlationId: .*").matcher(message);
            var correlationId = "";
            if (matcher.find()) {
                correlationId = matcher.group();
            }
            MDC.putCloseable("correlationId", correlationId
                    .replace("correlationId:", "").trim());

            logger.log(internalLevel, message);
        }
        ctx.write(msg, promise);
    }
}