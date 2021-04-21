package com.zhou.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.zhou.bean.VideoOrder;
import com.zhou.config.WeChatConfig;
import com.zhou.dto.VideoOrderDto;
import com.zhou.service.VideoOrderService;
import com.zhou.util.IpUtils;
import com.zhou.util.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

/**
 * @Author：周申宇
 * @Date:2021/4/5 20:59
 * @Decription:
 */
@RequestMapping("order")
@Controller
public class VideoOrderController {

    @Autowired
    private VideoOrderService videoOrderService;

    /**
     * 下单接口
     * @param videoId 视频id
     * @param request  用户信息
     * @return
     * @throws Exception
     */
    @GetMapping("saveOrder")
    public void saveOrder(@RequestParam(value = "video_id",required = true) int videoId,
                          HttpServletRequest request,
                          HttpServletResponse response) throws Exception {

        //记录用户下单ip
        //如果使用reques去拿ip不严谨，容易出现拿不到的情况，会过滤一些http头信息

        //获取ip
        String ip = IpUtils.getIpAddr(request);


        //获取用户id 这里是我的项目里加了jwt登陆 你可以直接写一次参数传
//        Integer userId = (Integer)request.getAttribute("user_id");

        VideoOrderDto videoOrderDto = new VideoOrderDto();
        videoOrderDto.setVideoId(videoId);
        videoOrderDto.setUserId(1);
        videoOrderDto.setIp(ip);

        //统一下单拿支付交易链接codeUrl
        String codeUrl = videoOrderService.saveVideoOrder(videoOrderDto);
        if(null == codeUrl){
            throw new NullPointerException();
        }

        try {
            //生成二维码配置
            Map<EncodeHintType,Object> hints = new HashMap<>();
            //设置纠错等级
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            //设置编码类型
            hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
            //构造图片对象
            BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE,400,400,hints);
            //输出流
            OutputStream out = response.getOutputStream();

            MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig();
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);

            System.out.println(image);

            MatrixToImageWriter.writeToStream(bitMatrix,"png",out);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    /**
     * @Auther: 周申宇
     * @Date: 2021/4/7 10:15
     * @Description:
     */
    @RequestMapping ("callback")
    public void orderCallback(HttpServletResponse response, HttpServletRequest request) throws Exception{

        //获取流信息
        InputStream inputStream = request.getInputStream();

        //转换 比inputStream更快 包装设计模式 性能更高
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
        //进行缓冲
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = in.readLine())!=null){
            sb.append(line);
        }
        in.close();
        inputStream.close();
        Map<String,String> callbackMap = WXPayUtil.xmlToMap(sb.toString());

        //map转sortedMap
        SortedMap<String,String> sortedMap = WXPayUtil.getSortedMap(callbackMap);

        //判断签名是否正确
        if(WXPayUtil.isCorrectSign(sortedMap, WeChatConfig.key)){
            System.out.println("OK");
            //判断业务状态是否正确
            if("SUCCESS".equals(sortedMap.get("result_code"))){

                String outTradeNo = sortedMap.get("out_trade_no");

                //使用队列方式提高性能
                VideoOrder dbVideoOrder = videoOrderService.findByOutTradeNo(outTradeNo);

                //更新订单状态
                if(dbVideoOrder !=null && dbVideoOrder.getState()==0){//判断逻辑看业务场景
                    System.out.println("支付成功回调");
                    System.out.println("处理业务");

                    //判断影响行数 row==1 或者row==0 响应微信成功或者失败
                    response.setContentType("text/xml");
                    response.getWriter().println("success");
                }
            }
        }
        //都处理失败
        response.setContentType("text/xml");
        response.getWriter().println("fail");
    }

}
