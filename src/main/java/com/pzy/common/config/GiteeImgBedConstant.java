//package com.pzy.common.config;
//
///**
// * @Author Nice
// * @Date 2021/7/21 15:02
// * gitee图床常量类
// */
//
//public class GiteeImgBedConstant {
//    /**
//     * TODO：这个常量是码云为您分配的私人令牌，Token  这里的代码会报错，仅仅是为了提醒您进行修改
//     */
//    String ACCESS_TOKEN =
//
//    /**
//     * 仓库所属地址  这个是您的私人用户名 具体请参考创建仓库时的注意事项
//     */
//    String OWNER =
//    /**
//     * TODO：仓库名称  这里是您的仓库名称
//     */
//    String REPO_NAME =
//    /**
//     * TODO： 上传图片的message
//     */
//    String CREATE_REPOS_MESSAGE = "add img";
//    /**
//     * TODO：文件前缀
//     */
//    String IMG_FILE_DEST_PATH = "/img/" + DateUtil.format(new Date(), "yyyy_MM_dd") + "/";
//
//    /**
//     * 新建文件
//     * <p>
//     * owner*   仓库所属空间地址(企业、组织或个人的地址path)
//     * repo*    仓库路径
//     * path*    文件的路径
//     * content* 文件内容, 要用 base64 编码
//     * message* 提交信息
//     * <p>
//     * %s =>仓库所属空间地址(企业、组织或个人的地址path)  (owner)
//     * %s => 仓库路径(repo)
//     * %s => 文件的路径(path)
//     */
//    String CREATE_REPOS_URL = "https://gitee.com/api/v5/repos/%s/%s/contents/%s";
//    /**
//     * 请求建立page  如果建立了，可以刷新
//     * <p>
//     * owner*  仓库所属空间地址(企业、组织或个人的地址path)
//     * repo*    仓库
//     */
//    String BUILD_PAGE_URL = "https://gitee.com/api/v5/repos/%s/%s/pages/builds";
//    /**
//     * TODO： gitpage请求路径
//     * 示例："http://apk2sf.gitee.io/typechoblogimg/";
//     */
//    String GITPAGE_REQUEST_URL =
//
//}
