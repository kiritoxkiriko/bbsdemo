package com.wxm.bbsdemo.service;

import java.util.Date;

public interface AuthorityService {
    public boolean isAdmin(Long userId);

    public boolean isModeratorOf(Long boardId,Long userId);

    public boolean isAssistantModeratorOf(Long boardId,Long userId);

    public boolean isPosterOf(Long postId,Long userId);

    public boolean isReplyerOf(Long postReplyId,Long userId);

    public boolean isCommenterOf(Long postReplyCommentId,Long userId);

    public boolean hasRightModifyBoard(Long userId,Long boardId);

    public boolean hasRightEditAssistantModerator(Long userId,Long boardId);

    public boolean hasRightEditModerator(Long userId,Long boardId);

    public boolean hasRightModifyPost(Long userId,Long postId);

    public boolean hasRightModifyPostReply(Long userId,Long postReplyId);

    public boolean hasRightDeletePostReplyComment(Long userId,Long postReplyCommentId);

    public boolean hasRightDeletePostReply(Long userId,Long postReplyId);

    public boolean hasRightDeletePost(Long userId,Long postId);

    public boolean hasRightBanOnBoard(Long userId,Long boardId);

    public boolean hasRightPostOnBoard(Long userId,Long boardId);


}
