package hip_pop.community.controller;

import hip_pop.community.domain.Post;
import hip_pop.community.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public String getPosts(Model model) {
        return "posts/postList";
    }

    @GetMapping("/posts/new")
    public String createPost(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "posts/createPostForm";
    }

    @PostMapping("/posts/new")
    public String create(@RequestParam("memberId") Long memberId,
                         @Valid PostForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "posts/createPostForm";
        }

        postService.join(memberId, form.getTitle(), form.getContent());

        return "redirect:/";
    }
}
