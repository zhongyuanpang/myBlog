//package com.pzy.web.admin;
//
//import com.pzy.util.Result;
//import jdk.vm.ci.hotspot.HotSpotCompilationRequestResult;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import cn.hutool.core.codec.Base64;
//import cn.hutool.core.util.IdUtil;
//import cn.hutool.http.HttpUtil;
//import cn.hutool.json.JSONObject;
//import cn.hutool.json.JSONUtil;
//import io.swagger.annotations.Api;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @Author Nice
// * @Date 2021/7/21 15:04
// */
//
//@Slf4j
//@RestController
//@Api(description = "码云博客图床管理接口")
//@RequestMapping("/api/giteeBlogImg")
//@Transactional
//public class GiteeBlogImgMController {
//
//    @RequestMapping("saveImg")
//    @ResponseBody
//    public Result<Map<String, Object>> saveImg(@RequestParam(value = "imgFile", required = true) MultipartFile imgFile) throws Exception {
//        Result<Map<String, Object>> result = ResultUtil.success("请求成功");
//
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//
//        String trueFileName = imgFile.getOriginalFilename();
//
//        assert trueFileName != null;
//        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));
//
//        String fileName = System.currentTimeMillis() + "_" + IdUtil.randomUUID() + suffix;
//
//
//        String paramImgFile = Base64.encode(imgFile.getBytes());
//
//        //转存到gitee
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("access_token", GiteeImgBedConstant.ACCESS_TOKEN);
//        paramMap.put("message", GiteeImgBedConstant.CREATE_REPOS_MESSAGE);
//        paramMap.put("content", paramImgFile);
//
//        String targetDir = GiteeImgBedConstant.IMG_FILE_DEST_PATH + fileName;
//        String requestUrl = String.format(GiteeImgBedConstant.CREATE_REPOS_URL, GiteeImgBedConstant.OWNER,
//                GiteeImgBedConstant.REPO_NAME, targetDir);
//
//        System.out.println(requestUrl);
//
//
//        String resultJson = HttpUtil.post(requestUrl, paramMap);
//
//        JSONObject jsonObject = JSONUtil.parseObj(resultJson);
//        if (jsonObject.getObj("commit") != null) {
//            String resultImgUrl = GiteeImgBedConstant.GITPAGE_REQUEST_URL + targetDir;
//            resultMap.put("resultImgUrl", resultImgUrl);
//            System.out.println(resultJson);
//            result.setCode(200);
//        } else {
//            result.setCode(400);
//        }
//        result.setResult(resultMap);
//
//        return result;
//    }
//
//    @RequestMapping("refreshPage")
//    @ResponseBody
//    public Result<Object> refreshPage() throws Exception {
//        Result<Object> result = ResultUtil.success("成功");
//
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("access_token", GiteeImgBedConstant.ACCESS_TOKEN);
//
//        String requestUrl = String.format(GiteeImgBedConstant.BUILD_PAGE_URL,
//                GiteeImgBedConstant.OWNER, GiteeImgBedConstant.REPO_NAME);
//
//        System.out.println(requestUrl);
//
//        Map<String, Object> resultMap = new HashMap<>();
//        String resultJson = HttpUtil.post(requestUrl, paramMap);
//
//        JSONObject jsonObject = JSONUtil.parseObj(resultJson);
//        if (jsonObject.getStr("status") != null) {
//            String notice = jsonObject.getStr("notice");
//            if (notice != null) {
//                if ("Deployed frequently".equalsIgnoreCase(notice)) {
//                    resultMap.put("message", "部署频繁");
//                    result.setCode(404);
//                } else {
//                    resultMap.put("message", "其他错误");
//                }
//                result.setCode(404);
//
//            }
//        } else {
//            result.setCode(200);
//        }
//
//
//        System.out.println(resultJson);
//
//        return result;
//    }
//}
