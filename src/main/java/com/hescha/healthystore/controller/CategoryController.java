package com.hescha.healthystore.controller;

import com.hescha.healthystore.model.Category;
import com.hescha.healthystore.model.Product;
import com.hescha.healthystore.service.CategoryService;
import com.hescha.healthystore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
@RequestMapping(CategoryController.CURRENT_ADDRESS)
public class CategoryController {
    public static final String API_PATH = "category";
    public static final String CURRENT_ADDRESS = "/" + API_PATH;
    public static final String MESSAGE = "message";
    public static final String THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE = API_PATH;
    public static final String THYMELEAF_TEMPLATE_EDIT_PAGE = THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE + "-edit";
    public static final String REDIRECT_TO_ALL_ITEMS = "redirect:" + CURRENT_ADDRESS;

    private final CategoryService service;

    private final ProductService productService;

    @GetMapping
    public String readAll(Model model) {
        model.addAttribute("list", service.readAll());
        return THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE;
    }

    @GetMapping(path = {"/edit", "/edit/{id}"})
    public String editPage(Model model, @PathVariable(name = "id", required = false) Long id) {
        if (id == null) {
            model.addAttribute("entity", new Category());
        } else {
            model.addAttribute("entity", service.read(id));
        }
        return THYMELEAF_TEMPLATE_EDIT_PAGE;
    }

    @PostMapping
    public String save(@ModelAttribute Category entity, RedirectAttributes ra) {
        if (entity.getId() == null) {
            try {
                Category createdEntity = service.create(entity);
                ra.addFlashAttribute(MESSAGE, "Creating is successful");
            } catch (Exception e) {
                ra.addFlashAttribute(MESSAGE, "Creating failed");
                e.printStackTrace();
            }
        } else {
            try {
                service.update(entity.getId(), entity);
                ra.addFlashAttribute(MESSAGE, "Editing is successful");
            } catch (Exception e) {
                e.printStackTrace();
                ra.addFlashAttribute(MESSAGE, "Editing failed");
            }
        }
        return REDIRECT_TO_ALL_ITEMS;
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            Category category = service.read(id);
            List<Product> byCategory = productService.findByCategory(category);
            List<Product> deletedProduct = byCategory.stream()
                    .filter(Product::getDeleted)
                    .collect(Collectors.toList());
            if (byCategory.size() == deletedProduct.size()) {
                byCategory.forEach(productService::delete);
                service.delete(id);
                ra.addFlashAttribute(MESSAGE, "Removing is successful");
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute(MESSAGE, "You cannot remove category if it have at least 1 product");
        }
        return REDIRECT_TO_ALL_ITEMS;
    }
}
