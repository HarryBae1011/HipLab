package hip_pop.community.service;

import hip_pop.community.domain.Comment;
import hip_pop.community.domain.Member;
import hip_pop.community.domain.Post;
import hip_pop.community.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    /**
     * 댓글 등록
     */
    @Transactional
    public Long join(Post post, Member member, String content) {

        Comment comment = Comment.createComment(post, member, content);

        commentRepository.save(comment);

        return comment.getId();
    }

    //댓글 저장
    @Transactional
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    //댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    //단일 댓글 찾기
    @Transactional
    public Comment findSingleComment(Long commentId) {
        return commentRepository.findOne(commentId);
    }

    /*
    //전체 댓글 찾기
    @Transactional
    public List<Comment> findAllComments(Long postId) {
        return commentRepository
    }
     */
}
