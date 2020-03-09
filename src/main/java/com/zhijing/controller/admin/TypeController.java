package com.zhijing.controller.admin;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zhijing.pojo.Type;
import com.zhijing.service.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    TypeServiceImpl typeService;

    private static Integer page = 0;
    private static Integer pageSize = 10;
    @RequestMapping("/types")
    public String list(Model model){
//        List<Type> allType = typeService.getAllType();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("startIndex",0);
        map.put("pageSize",pageSize);
        List<Type> limitType = typeService.getLimitType(map);
        model.addAttribute("types",limitType);
        return "admin/types";
    }

    @RequestMapping("/toUpdatePage/{id}")
    public String toUpdatePage(@PathVariable("id") Long id, Model model){
        Type type = typeService.getType(id);
        long typeId = type.getId();
        String typeName = type.getName();
        model.addAttribute("typeName",typeName);
        model.addAttribute("typeId",typeId);
        return "admin/type-update";
    }

    @PostMapping("/updateType")
    public String updateType(@RequestParam Long id,
                             @RequestParam String name,
                             Model attributes,
                             RedirectAttributes redirectAttributes){
        Type typeByName = typeService.getTypeByName(name);
        if (typeByName != null){
            attributes.addAttribute("updateMsg","分类名已经存在");
            return "admin/type-update";
        }else{
            if (name==null || name.equals("")){
                attributes.addAttribute("updateMsg","分类名不能为空");
                return "admin/type-update";
            }
            else{
                int type = typeService.updateType(id,name);
                redirectAttributes.addFlashAttribute("result","更新成功");
                return "redirect:/admin/types";
            }
        }



    }

    @RequestMapping("/toAddTypePage")
    public String toAddTypePage(Model model){
        /*
        Type lastType = typeService.getLastType();
        long id = lastType.getId();
        model.addAttribute("last")
        *
        * */
        return "admin/type-add";
    }

    @RequestMapping("/addType")
    public String addType(@RequestParam String name, Model attributes,RedirectAttributes redirectAttributes){
        Type typeByName = typeService.getTypeByName(name);
        System.out.println("typeByName==>"+typeByName);
        if (typeByName == null){
            Type lastType = typeService.getLastType();
            System.out.println("lastType" + lastType);
            long lastTypeid = lastType.getId();
            Long id = lastTypeid + 1;
            Type type = new Type(id, name);
            System.out.println(type);
            if (name==null || name.equals("")){
                attributes.addAttribute("addMsg","分类名称不能为空");
                return "admin/type-add";
            }else{
                redirectAttributes.addFlashAttribute("result","添加成功，标签名:"+name);
                typeService.saveType(type);
                return "redirect:types";
            }
        }else{
            attributes.addAttribute("addMsg","分类名已经存在");
            return "admin/type-add";
        }
    }

    @RequestMapping("/deletedType/{id}")
    public String deletedType(@PathVariable("id") Long id,RedirectAttributes attributes){
        typeService.deletedType(id);
        attributes.addFlashAttribute("result","删除成功");
        return "redirect:/admin/types";
    }

    @RequestMapping("/nextPage")
    public String nextPage(Model model){
        HashMap<String, Integer> map = new HashMap<>();
        page = page+5;
        map.put("startIndex",page);
        map.put("pageSize",pageSize);
        List<Type> limitType = typeService.getLimitType(map);
        model.addAttribute("types",limitType);
        return "admin/types";
    }

    @RequestMapping("/previousPage")
    public String previousPage(Model model){
        HashMap<String, Integer> map = new HashMap<>();
        page = page-5;
        if (page<=0){
            page=0;
            System.out.println(page);
            model.addAttribute("page","这已经是第一页了");
            return "redirect:types";
        }
        map.put("startIndex",page);
        map.put("pageSize",pageSize);
        List<Type> limitType = typeService.getLimitType(map);
        model.addAttribute("types",limitType);
        return "admin/types";
    }
}
