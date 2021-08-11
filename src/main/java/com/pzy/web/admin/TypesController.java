package com.pzy.web.admin;

import com.pzy.pojo.Type;
import com.pzy.service.TypeService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypesController {
    @Autowired
    private TypeService typeService;


    @GetMapping("/types")
                        /**
                         * 该意思为页面根据size大小显示多少条数据，sort根据id排序,direction:倒序
                         * */
    public String types(@PageableDefault(size = 5
                            ,sort = {"id"}
                            ,direction = Sort.Direction.DESC)
                            Pageable pageable
                            , Model model){

        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }


    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }


/**
 * @author : pangzy
 * @date : 2021/6/9 10:40
 * @return : java.lang.String
 * 编辑标签
 */
@Transactional      //注意不加这个会报错
@GetMapping(value = "/types/{id}/input")
public String editInput(@PathVariable Long id, Model model) {
    Type type = typeService.getType(id);
    model.addAttribute("type", type);
    return "admin/types-input";
}

/**
 * @author : pangzy
 * @date : 2021/6/9 10:45
 * @return : void
 * 删除标签
 */
@GetMapping("/types/{id}/delete")
public String delete(@PathVariable Long id,RedirectAttributes attributes) {
    typeService.deleteType(id);
    attributes.addFlashAttribute("message", "Deleted");
    return "redirect:/admin/types";
}

/**
 * @author : pangzy
 * @date : 2021/6/9 11:24
 * @return : java.lang.String
 * 修改标签
 */
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            result.rejectValue("name","nameError","Type already exists");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        Type t = typeService.updateType(id, type);
        if (t == null ) {
            attributes.addFlashAttribute("message", "Update fail");
        } else {
            attributes.addFlashAttribute("message", "Update success");
        }
        return "redirect:/admin/types";
    }

/**
 * @author : pangzy
 * @date : 2021/6/9 10:40
 * @return : java.lang.String
 * 添加分类标签
 */
    @PostMapping("/types")
                    //@Valid代表要校验对象,BindingResult：校验以后的结果
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        Type t1 = typeService.getTypeByName(type.getName());
        if(t1 != null ){
            result.rejectValue("name","nameError","不能重复添加分类");
        }
        //如果有错误
        if(result.hasErrors()){
            return "admin/types-input";
        }
        Type t = typeService.saveType(type);
        if ( t == null) {
            attributes.addFlashAttribute("message","新增失败");
        } else{
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }


}
