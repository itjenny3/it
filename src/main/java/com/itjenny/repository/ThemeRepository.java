package com.itjenny.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itjenny.domain.Theme;

public interface ThemeRepository extends JpaRepository<Theme, String> {
}