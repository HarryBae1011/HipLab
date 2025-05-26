package hip_pop.community.web.controller;

import hip_pop.community.domain.Member;
import hip_pop.community.domain.Post;
import hip_pop.community.domain.enums.PostCategory;
import hip_pop.community.service.MemberService;
import hip_pop.community.service.PostLikeService;
import hip_pop.community.service.PostService;
import hip_pop.community.web.dto.PostForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final MemberService memberService;
    private final PostLikeService postLikeService;

    //전체 포스트 조회
    @GetMapping
    public String getPosts(@RequestParam(required = false) PostCategory category,
                           Model model) {
        List<Post> posts;

        if (category != null) {
            posts = postService.findPostByCategory(category);
        } else {
            posts = postService.findAllPosts();
        }

        model.addAttribute("posts", posts);
        model.addAttribute("selectedCategory", category);
        return "posts/postList";
    }

    //단일 포스트 조회
    @GetMapping("/{postId}")
    public String getSinglePost(@PathVariable("postId") Long postId, Model model,
                                Principal principal) throws Exception {
        Post singlePost = postService.findSinglePost(postId);
        Member findMember = memberService.getMember(principal.getName());
        Integer likes = postLikeService.countLikes(postId);

        model.addAttribute("post", singlePost);
        model.addAttribute("loginMember", findMember);
        model.addAttribute("postLike", likes);
        return "posts/singlePost";
    }

    //새로운 포스트 폼
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/new")
    public String createPost(Model model) {
        model.addAttribute("postForm", new PostForm());
        model.addAttribute("categories", PostCategory.values());

        return "posts/createPostForm";
    }

    //새로운 포스트 만들기
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/new")
    public String create(@Valid PostForm form, BindingResult bindingResult,
                         Principal principal) throws Exception {
        if (bindingResult.hasErrors()) {
            return "posts/createPostForm";
        }

        Member member = memberService.getMember(principal.getName());

        postService.join(form.getCategory(), form.getTitle(), form.getContent(), member);

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

    //포스트 좋아요 누르기
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{postId}/like")
    public String postLike(@PathVariable("postId") Long postId, Model model,
                                Principal principal) throws Exception {
        Post post = postService.findSinglePost(postId);
        Member member = memberService.getMember(principal.getName());

        postLikeService.pressLike(post, member);
        Integer likes = postLikeService.countLikes(postId);
        model.addAttribute("postLike", likes);
        return "redirect:/posts/{postId}";
    }
}
