package com.example.board.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
