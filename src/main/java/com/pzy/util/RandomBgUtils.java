package com.pzy.util;

/**
 * @Author Nice
 * @Date 2021/7/22 14:14
 */

public class RandomBgUtils {

    public static String randomAvatar(){
        String  bgArr[]  =  {
                "http://p5.qhimg.com/bdm/1024_768_85/t01cb9e3338c25c513b.jpg",
                "http://p1.qhimg.com/bdm/1024_768_85/t012ae76b1cbb515bb3.jpg",
                "http://p9.qhimg.com/bdm/1024_768_85/t0128fffe694eae1e3a.jpg",
                "http://p8.qhimg.com/bdm/1024_768_85/t018d7428a5ba932fae.jpg",
                "http://p5.qhimg.com/bdm/1024_768_85/t0103fc086b80dd785e.jpg",
                "http://p9.qhimg.com/bdm/1024_768_85/t017c3470ca37d2c018.jpg",
                "http://p7.qhimg.com/bdm/1024_768_85/t01e29dac15eee80937.jpg",
                "http://p1.qhimg.com/bdm/1024_768_85/t016941844bf3eb30a2.jpg",
                "http://p9.qhimg.com/bdm/1024_768_85/t014a2ab737194cc459.jpg",
                "http://p1.qhimg.com/bdm/1024_768_85/t0158d0197da6c49837.jpg",
                "http://p8.qhimg.com/bdm/1024_768_85/t013f7d52a56b987ebf.jpg",
                "http://p1.qhimg.com/bdm/1024_768_85/t0179f90b2727c42e3c.jpg",
                "http://p7.qhimg.com/bdm/1024_768_85/t015606b889bb8b0629.jpg",
                "http://p9.qhimg.com/bdm/1024_768_85/t01a2045f8aaca9e6d3.jpg",
                "http://p8.qhimg.com/bdm/1024_768_85/t0110107bb0a3f5c8bf.jpg",
                "http://p1.qhimg.com/bdm/1024_768_85/t01c27d42c762f5a79b.jpg",
                "http://p0.qhimg.com/bdm/1024_768_85/t018e0d7d9553142c1c.jpg",
                "http://p1.qhimg.com/bdm/1024_768_85/t018a2c02a3d0d07b3d.jpg",
                "http://p1.qhimg.com/bdm/1024_768_85/t0114fa52040c17dbb6.jpg",
                "http://p8.qhimg.com/bdm/1024_768_85/t01314ee98929b7a469.jpg",
                "http://p1.qhimg.com/bdm/1024_768_85/t016eeac8392dcdaa69.jpg",
                "http://p8.qhimg.com/bdm/1024_768_85/t012185a3c0d19abf4d.jpg",
                "http://p9.qhimg.com/bdm/1024_768_85/t01ef46cadde33786be.jpg",
                "http://p3.qhimg.com/bdm/1024_768_85/t01f11af7938fa72dd3.jpg",
                "http://p8.qhimg.com/bdm/1024_768_85/t0196fcd97fce72a4d6.jpg",
                "http://p1.qhimg.com/bdm/1024_768_85/t01cce746262dcfe657.jpg",
                "http://p2.qhimg.com/bdm/1024_768_85/t012962abcf9cb49218.jpg",
                "http://p6.qhimg.com/bdm/1024_768_85/t018fd4abd5fcb5f699.jpg",
                "http://p3.qhimg.com/bdm/1024_768_85/t0162230f9b78e4a7a6.jpg",
                "http://p3.qhimg.com/bdm/1024_768_85/t01ca803eb1731ab0bc.jpg",
                "http://p0.qhimg.com/bdm/1024_768_85/t01e0d6de69f2603db6.jpg",
                "http://p2.qhimg.com/bdm/1024_768_85/t01975146959e0ebd75.jpg",
                "http://p9.qhimg.com/bdm/1024_768_85/t0169d6001972310e45.jpg",
                "http://p8.qhimg.com/bdm/1024_768_85/t013ff88d6bdf1e8c14.jpg",
                "http://p4.qhimg.com/bdm/1024_768_85/t0183c4737edc0cc40e.jpg",
                "http://p3.qhimg.com/bdm/1024_768_85/t0125feb14de1009121.jpg",
                "http://p8.qhimg.com/bdm/1024_768_85/t0155d2f2b0493ca6f5.jpg",
                "http://p8.qhimg.com/bdm/1024_768_85/t01e1694c332131d5fe.jpg",
                "http://p9.qhimg.com/bdm/1024_768_85/t014d3a287fa6e51193.jpg"
        };
        int index = (int) (Math.random()*bgArr.length);
        String bg = bgArr[index];
        return bg;
    }
}
