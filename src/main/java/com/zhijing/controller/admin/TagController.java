package com.zhijing.controller.admin;

import com.zhijing.pojo.Tag;
import com.zhijing.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    TagService tagService;

    private static Integer page = 0;
    private static Integer pageSize = 5;

    @GetMapping("/tags")
    public String list(Model model){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("startIndex",0);
        map.put("pageSize",pageSize);
        List<Tag> limitTag = tagService.getLimitTag(map);
        model.addAttribute("tags",limitTag);
        model.addAttribute("page",0);
        return "admin/tags";
    }

    @GetMapping("/toUpdateTagPage/{id}")
    public String toUpdateTagPage(@PathVariable("id") Long id,Model model){
        Tag tag = tagService.getTag(id);
        model.addAttribute("tagName",tag.getName());
        model.addAttribute("tagId",tag.getId());
        return "admin/tag-update";
    }

    @PostMapping("/updateTag")
    public String updateTag(@RequestParam Long id,
                            @RequestParam String name,
                            RedirectAttributes attributes,
                            Model model){
        Tag tagByName = tagService.getTagByName(name);
        if (tagByName == null){
            if (name == null || name.equals("")){
                model.addAttribute("updateMsg","标签名不能为空");
                return "admin/tag-update";
            }else{
                System.out.println("id:"+id);
                System.out.println("name:"+name);
                tagService.updateTag(id, name);
                attributes.addFlashAttribute("result","标签更新成功");
                return "redirect:/admin/tags";
            }
        }else{
            model.addAttribute("updateMsg","标签名已存在");
            return "admin/tag-update";
        }
    }

    @GetMapping("/toAddTagPage")
    public String toAddTagPage(){
        return "admin/tag-add";
    }

    @PostMapping("/addTag")
    public String addTag(
                         @RequestParam("name") String name,
                         RedirectAttributes attributes,
                         Model model){
        Tag tagByName = tagService.getTagByName(name);
        if (tagByName == null){
            if (name == null || name.equals("")){
                model.addAttribute("addMsg","标签名不能为空");
                return "admin/tag-add";
            }else{
                Tag lastTag = tagService.getLastTag();
                Long id = lastTag.getId() + 1;
                tagService.saveTag(new Tag(id,name));
                attributes.addFlashAttribute("result","标签添加成功");
                return "redirect:/admin/tags";
            }
        }else{
            model.addAttribute("addMsg","标签名以存在");
            return "admin/tag-add";
        }
    }

    @RequestMapping("/deletedTag/{id}")
    public String deletedTag(@PathVariable("id") Long id,RedirectAttributes attributes){

        tagService.deletedTag(id);
        attributes.addFlashAttribute("result","标签删除成功");

        return "redirect:/admin/tags";
    }


    @GetMapping("/previousTagPage")
    public String previousTagPage(Model model){

        if (page<0){
            return "redirect:/admin/tags";
        }
        HashMap<String, Integer> map = new HashMap<>();
        page = page-5;
        map.put("startIndex",page);
        map.put("pageSize",pageSize);
        List<Tag> limitTag = tagService.getLimitTag(map);
        model.addAttribute("tags",limitTag);
        model.addAttribute("page",page);
        return "admin/tags";
    }

    @GetMapping("/nextTagPage")
    public String nextTagPage(Model model){

        HashMap<String, Integer> map = new HashMap<>();
        page = page+5;
        map.put("startIndex",page);
        map.put("pageSize",pageSize);
        List<Tag> limitTag = tagService.getLimitTag(map);
        model.addAttribute("tags",limitTag);
        model.addAttribute("page",page);
        return "admin/tags";
    }
}
