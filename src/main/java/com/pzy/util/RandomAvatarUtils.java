package com.pzy.util;

import org.springframework.stereotype.Service;

/**
 * @Author Nice
 * @Date 2021/7/22 14:14
 */

public class RandomAvatarUtils {

    public static String randomAvatar(){
        String  avatarArr[]  =  {
                "https://img2.baidu.com/it/u=2886223298,2151992788&fm=26&fmt=auto&gp=0.jpg",
                "https://img1.baidu.com/it/u=467501646,1630686934&fm=26&fmt=auto&gp=0.jpg",
                "https://img1.baidu.com/it/u=8306793,1969814800&fm=26&fmt=auto&gp=0.jpg",
                "https://img1.baidu.com/it/u=752912585,3836952519&fm=26&fmt=auto&gp=0.jpg",
                "https://img1.baidu.com/it/u=1306810397,13881722&fm=26&fmt=auto&gp=0.jpg",
                "https://img2.baidu.com/it/u=2196531070,513850591&fm=26&fmt=auto&gp=0.jpg",
                "https://img0.baidu.com/it/u=3937377200,1833483299&fm=11&fmt=auto&gp=0.jpg",
                "https://img0.baidu.com/it/u=1898338732,1169506520&fm=26&fmt=auto&gp=0.jpg",
                "https://img1.baidu.com/it/u=2457467094,3866988422&fm=26&fmt=auto&gp=0.jpg",
                "https://img2.baidu.com/it/u=2226359662,2129159393&fm=26&fmt=auto&gp=0.jpg",
                "https://img0.baidu.com/it/u=786469097,2210809279&fm=11&fmt=auto&gp=0.jpg",
                "https://img2.baidu.com/it/u=1253773003,1450602676&fm=26&fmt=auto&gp=0.jpg",
                "https://img0.baidu.com/it/u=526367469,3278989653&fm=26&fmt=auto&gp=0.jpg",
                "https://img1.baidu.com/it/u=3747249113,2611241841&fm=26&fmt=auto&gp=0.jpg",
                "https://img2.baidu.com/it/u=222120700,1745793916&fm=26&fmt=auto&gp=0.jpg",
                "https://img2.baidu.com/it/u=2642173617,1148729882&fm=26&fmt=auto&gp=0.jpg",
                "https://img1.baidu.com/it/u=311381995,3515274849&fm=26&fmt=auto&gp=0.jpg",
                "https://img0.baidu.com/it/u=1576448082,270003975&fm=26&fmt=auto&gp=0.jpg",
                "https://img2.baidu.com/it/u=2542882366,3643076837&fm=26&fmt=auto&gp=0.jpg",
                "https://img0.baidu.com/it/u=2011577546,2785136063&fm=26&fmt=auto&gp=0.jpg",
                "https://img1.baidu.com/it/u=3767111262,2708566925&fm=26&fmt=auto&gp=0.jpg",
                "https://img0.baidu.com/it/u=1415887207,2981715027&fm=26&fmt=auto&gp=0.jpg",
                "https://img2.baidu.com/it/u=4251059536,2165781887&fm=26&fmt=auto&gp=0.jpg",
                "https://img1.baidu.com/it/u=1166503026,41947489&fm=26&fmt=auto&gp=0.jpg",
                "https://img2.baidu.com/it/u=2857291761,603812366&fm=26&fmt=auto&gp=0.jpg",
                "https://img0.baidu.com/it/u=534385950,1947072942&fm=11&fmt=auto&gp=0.jpg",
                "https://img0.baidu.com/it/u=1554266037,1179582060&fm=26&fmt=auto&gp=0.jpg",
                "https://img0.baidu.com/it/u=2848151259,330236343&fm=26&fmt=auto&gp=0.jpg",
                "https://img2.baidu.com/it/u=3123988443,2050046850&fm=26&fmt=auto&gp=0.jpg",
                "https://img2.baidu.com/it/u=3609671807,461735196&fm=26&fmt=auto&gp=0.jpg",
                "https://img2.baidu.com/it/u=1547456850,3575791287&fm=26&fmt=auto&gp=0.jpg",
                "https://img2.baidu.com/it/u=3071317428,985641824&fm=26&fmt=auto&gp=0.jpg",
                "https://img0.baidu.com/it/u=3996123347,1202756609&fm=26&fmt=auto&gp=0.jpg",
                "https://img1.baidu.com/it/u=4049017199,2645921319&fm=26&fmt=auto&gp=0.jpg",
                "https://img1.baidu.com/it/u=722588439,2165056576&fm=26&fmt=auto&gp=0.jpg",
                "https://img2.baidu.com/it/u=1704219071,3761829583&fm=26&fmt=auto&gp=0.jpg",
                "https://img2.baidu.com/it/u=3513353926,1521512087&fm=26&fmt=auto&gp=0.jpg",
                "https://img2.baidu.com/it/u=3233789257,2569021433&fm=26&fmt=auto&gp=0.jpg",
                "https://img0.baidu.com/it/u=2707928180,2720343818&fm=26&fmt=auto&gp=0.jpg"
        };
        int index = (int) (Math.random()*avatarArr.length);
        String avatar = avatarArr[index];
        return avatar;
    }
}
