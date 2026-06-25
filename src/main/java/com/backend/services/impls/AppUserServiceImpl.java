package com.backend.services.impls;

import com.backend.repositories.AppUserRepository;
import com.backend.services.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {


    private final AppUserRepository userRepository;
}
