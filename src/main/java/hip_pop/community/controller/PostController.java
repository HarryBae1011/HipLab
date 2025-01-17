package hip_pop.community.controller;

import hip_pop.community.domain.Post;
import hip_pop.community.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    //전체 포스트 조회
    @GetMapping
    public String getPosts(Model model) {
        List<Post> findPosts = postService.findPosts();

        model.addAttribute("posts", findPosts);
        return "posts/postList";
    }

    //단일 포스트 조회
    @GetMapping("/{postId}")
    public String getSinglePost(@PathVariable("postId") Long postId, Model model) {
        Post singlePost = postService.findSinglePost(postId);

        model.addAttribute("post", singlePost);
        return "posts/singlePost";
    }

    //새로운 포스트 폼
    @GetMapping("/new")
    public String createPost(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "posts/createPostForm";
    }

    //새로운 포스트 만들기
    @PostMapping("/new")
    public String create(//@RequestParam("memberId") Long memberId,
                         @Valid PostForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "posts/createPostForm";
        }

        postService.join(//memberId,
                         form.getTitle(), form.getContent());

        return "redirect:/posts";
    }

    //포스트 수정 폼
    @GetMapping("/edit/{postId}")
    public String editPost(@PathVariable("postId") Long postId, Model model) {
        Post singlePost = postService.findSinglePost(postId);

        model.addAttribute("post", singlePost);
        return "posts/editPostForm";
    }

    //포스트 수정 하기
    @PostMapping("/edit/{postId}")
    public String edit(@PathVariable("postId") Long postId,
                       @RequestParam String title,
                       @RequestParam String content) {
        Post singlePost = postService.findSinglePost(postId);
        singlePost.setTitle(title);
        singlePost.setContent(content);

        postService.savePost(singlePost);
        return "redirect:/posts/{postId}";
    }

    //포스트 삭제 하기
    //Delete 요청으로 와야 되는데 요청이 post에서 안 바뀌어서 일단 PostMapping으로 처리
    @PostMapping("/delete/{postId}")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return "redirect:/posts";
    }
}
