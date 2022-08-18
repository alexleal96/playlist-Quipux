package com.playlist.quipux.infraestructure.drivenadapters.webclient;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.SocketAddress;
import java.nio.charset.Charset;

class HttpLoggingHandlerTest {

   private HttpLoggingHandler httpLoggingHandler;

   @BeforeEach
   public void setup() {
       httpLoggingHandler = new HttpLoggingHandler();
   }

   @Test
    void formatByteBuf() {
       ChannelHandlerContext channelHandlerContext = Mockito.mock(ChannelHandlerContext.class);
       Channel channel = Mockito.mock(Channel.class);
       Mockito.when(channel.toString()).thenReturn("");
       Mockito.when(channelHandlerContext.channel()).thenReturn(channel);

       httpLoggingHandler.format(channelHandlerContext, "event", "");

       Mockito.verify(channelHandlerContext, Mockito.times(1)).channel();
   }

   @Test
    void channelRegistered() {
       ChannelHandlerContext channelHandlerContext = Mockito.mock(ChannelHandlerContext.class);

       httpLoggingHandler.channelRegistered(channelHandlerContext);

       Mockito.verify(channelHandlerContext, Mockito.times(1)).fireChannelRegistered();
   }

   @Test
    void connect() {
       ChannelHandlerContext channelHandlerContext = Mockito.mock(ChannelHandlerContext.class);
       SocketAddress remoteSocketAddress = Mockito.mock(SocketAddress.class);
       SocketAddress localSocketAddress = Mockito.mock(SocketAddress.class);
       ChannelPromise channelPromise = Mockito.mock(ChannelPromise.class);

       httpLoggingHandler.connect(channelHandlerContext, remoteSocketAddress, localSocketAddress, channelPromise);

       Mockito.verify(channelHandlerContext, Mockito.times(1))
               .connect(Mockito.any(), Mockito.any(), Mockito.any());
   }

   @Test
    void channelActive() {
       ChannelHandlerContext channelHandlerContext = Mockito.mock(ChannelHandlerContext.class);

       httpLoggingHandler.channelActive(channelHandlerContext);

       Mockito.verify(channelHandlerContext, Mockito.times(1)).fireChannelActive();
   }

   @Test
    void userEventTriggered() {
       ChannelHandlerContext channelHandlerContext = Mockito.mock(ChannelHandlerContext.class);

       httpLoggingHandler.userEventTriggered(channelHandlerContext, "");

       Mockito.verify(channelHandlerContext, Mockito.times(1))
               .fireUserEventTriggered(Mockito.any());
   }

   @Test
    void channelInactive() {
       ChannelHandlerContext channelHandlerContext = Mockito.mock(ChannelHandlerContext.class);

       httpLoggingHandler.channelInactive(channelHandlerContext);

       Mockito.verify(channelHandlerContext, Mockito.times(1)).fireChannelInactive();
   }

   @Test
    void channelUnregistered() {
       ChannelHandlerContext channelHandlerContext = Mockito.mock(ChannelHandlerContext.class);

       httpLoggingHandler.channelUnregistered(channelHandlerContext);

       Mockito.verify(channelHandlerContext, Mockito.times(1)).fireChannelUnregistered();
   }

   @Test
    void flush() {
       ChannelHandlerContext channelHandlerContext = Mockito.mock(ChannelHandlerContext.class);

       httpLoggingHandler.flush(channelHandlerContext);

       Mockito.verify(channelHandlerContext, Mockito.times(1)).flush();
   }

   @Test
    void channelReadComplete() {
       ChannelHandlerContext channelHandlerContext = Mockito.mock(ChannelHandlerContext.class);

       httpLoggingHandler.channelReadComplete(channelHandlerContext);

       Mockito.verify(channelHandlerContext, Mockito.times(1)).fireChannelReadComplete();
   }

   @Test
    void channelRead() {
       ChannelHandlerContext channelHandlerContext = Mockito.mock(ChannelHandlerContext.class);
       Channel channel = Mockito.mock(Channel.class);
       Mockito.when(channel.toString()).thenReturn("");
       Mockito.when(channelHandlerContext.channel()).thenReturn(channel);

       httpLoggingHandler.channelRead(channelHandlerContext, "");

       Mockito.verify(channelHandlerContext, Mockito.times(1)).fireChannelRead(Mockito.any());
   }

   @Test
    void write() {
       ChannelHandlerContext channelHandlerContext = Mockito.mock(ChannelHandlerContext.class);
       Channel channel = Mockito.mock(Channel.class);
       Mockito.when(channel.toString()).thenReturn("");
       Mockito.when(channelHandlerContext.channel()).thenReturn(channel);
       ByteBuf byteBuf = Mockito.mock(ByteBuf.class);
       Mockito.when(byteBuf.toString(Mockito.any(Charset.class))).thenReturn("correlationId: 123");
       ChannelPromise channelPromise = Mockito.mock(ChannelPromise.class);

       httpLoggingHandler.write(channelHandlerContext, byteBuf, channelPromise);

       Mockito.verify(channelHandlerContext, Mockito.times(1))
               .write(Mockito.any(), Mockito.any());
   }
}
