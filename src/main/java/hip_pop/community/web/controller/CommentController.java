package hip_pop.community.web.controller;

import hip_pop.community.domain.Comment;
import hip_pop.community.domain.Member;
import hip_pop.community.domain.Post;
import hip_pop.community.service.CommentService;
import hip_pop.community.service.MemberService;
import hip_pop.community.service.PostService;
import hip_pop.community.web.dto.CommentForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;
    private final MemberService memberService;

    //새로운 댓글 작성
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public String createComment(@Valid CommentForm form,
                                @PathVariable("postId") Long postId,
                                Principal principal) throws Exception {
        Post findPost = postService.findSinglePost(postId);
        Member findMember = memberService.getMember(principal.getName());

        commentService.join(findPost, findMember, form.getContent());

        return "redirect:/posts/{postId}";
    }

    //댓글 삭제
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable("postId") Long postId,
                                @PathVariable("commentId") Long commentId,
                                Principal principal) {
        Comment findComment = commentService.findSingleComment(commentId);

        if (findComment.getCommentMember().getName().equals(principal.getName())) {
            commentService.deleteComment(commentId);
        } else {
            throw new SecurityException("댓글 작성자만 삭제할 수 있습니다.");
        }

        return "redirect:/posts/{postId}";
    }

}
