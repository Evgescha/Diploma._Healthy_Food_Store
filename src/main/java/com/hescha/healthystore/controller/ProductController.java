package com.hescha.healthystore.controller;

import com.hescha.healthystore.model.Product;
import com.hescha.healthystore.service.CategoryService;
import com.hescha.healthystore.service.CommentService;
import com.hescha.healthystore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
@RequestMapping(ProductController.CURRENT_ADDRESS)
public class ProductController {
    public static final String API_PATH = "product";
    public static final String CURRENT_ADDRESS = "/" + API_PATH;
    public static final String MESSAGE = "message";
    public static final String THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE = API_PATH;
    public static final String THYMELEAF_TEMPLATE_ONE_ITEM_PAGE = THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE + "-one";
    public static final String THYMELEAF_TEMPLATE_EDIT_PAGE = THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE + "-edit";
    public static final String REDIRECT_TO_ALL_ITEMS = "redirect:" + CURRENT_ADDRESS;

    private final ProductService service;
    private final CategoryService categoryService;
    private final CommentService commentService;

    @GetMapping
    public String readAll(Model model) {
        model.addAttribute("list", service.readAll());
        return THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE;
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam String search) {
        model.addAttribute("list", service.findByNameContains(search));
        return THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE;
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long id, Model model) {
        model.addAttribute("entity", service.read(id));
        return THYMELEAF_TEMPLATE_ONE_ITEM_PAGE;
    }

    @GetMapping(path = {"/edit", "/edit/{id}"})
    public String editPage(Model model, @PathVariable(name = "id", required = false) Long id) {
        if (id == null) {
            model.addAttribute("entity", new Product());
        } else {
            model.addAttribute("entity", service.read(id));
        }

        model.addAttribute("category_list", categoryService.readAll());
        model.addAttribute("comment_list", commentService.readAll());

        return THYMELEAF_TEMPLATE_EDIT_PAGE;
    }

    @PostMapping
    public String save(@ModelAttribute Product entity, RedirectAttributes ra) {
        if (entity.getId() == null) {
            try {
                Product createdEntity = service.create(entity);
                ra.addFlashAttribute(MESSAGE, "Creating is successful");
                return REDIRECT_TO_ALL_ITEMS + "/" + createdEntity.getId();
            } catch (Exception e) {
                ra.addFlashAttribute(MESSAGE, "Creating failed");
                e.printStackTrace();
            }
            return REDIRECT_TO_ALL_ITEMS;
        } else {
            try {
                service.update(entity.getId(), entity);
                ra.addFlashAttribute(MESSAGE, "Editing is successful");
            } catch (Exception e) {
                e.printStackTrace();
                ra.addFlashAttribute(MESSAGE, "Editing failed");
            }
            return REDIRECT_TO_ALL_ITEMS + "/" + entity.getId();
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute(MESSAGE, "Removing is successful");
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute(MESSAGE, "Removing failed");
        }
        return REDIRECT_TO_ALL_ITEMS;
    }
}
