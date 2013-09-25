package com.itjenny.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itjenny.domain.Setting;

public interface SettingRepository extends JpaRepository<Setting, String> {
}
