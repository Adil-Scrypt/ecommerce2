package com.adilzhanabdambayev.ecommerce2.service;

import com.adilzhanabdambayev.ecommerce2.dto.AdilzhanAbdambayevAuthResponse;
import com.adilzhanabdambayev.ecommerce2.dto.AdilzhanAbdambayevLoginRequest;
import com.adilzhanabdambayev.ecommerce2.dto.AdilzhanAbdambayevRegisterRequest;

public interface AdilzhanAbdambayevAuthService {

    AdilzhanAbdambayevAuthResponse register(AdilzhanAbdambayevRegisterRequest request);

    AdilzhanAbdambayevAuthResponse login(AdilzhanAbdambayevLoginRequest request);
}
