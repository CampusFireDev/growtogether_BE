package com.campfiredev.growtogether.study.service.vote;

import com.campfiredev.growtogether.study.entity.vote.VoteEntity;

public interface VoteProcessor {
  void processVote(VoteEntity voteEntity, int votes, int totalSize);
}
