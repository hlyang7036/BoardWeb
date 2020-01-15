package com.example.Board.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Board.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
