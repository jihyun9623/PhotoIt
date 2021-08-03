package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.AuthorLocation;
import com.ssafy.db.entity.Location;
import com.ssafy.db.entity.MyStudio;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.AuthorLocationRepository;
import com.ssafy.db.repository.LocationRepository;
import com.ssafy.db.repository.MyStudioRepository;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private AuthorLocationRepository authorLocationRepository;
    @Autowired
    private MyStudioRepository myStudioRepository;

    @Override
    public void signUp(UserRegisterPostReq info) {
        // User 테이블에 저장
            // id, passwd, nickname, pg, photo
        // mystudio 테이블에 저장
            // nickname, profile
        // author_location 테이블에 저장
            // location 저장
        // location 테이블에 저장
            // location

        // 1. 작가회원이면 mystudio + location 생성
        if(info.getPg()==true){
            // 2-1. mystudio 생성
            MyStudio myStudio=MyStudio.builder()
                    .nickname(info.getNickname())
                    .profile(info.getProfile())
                    .build();
            // 이거는...저장이아닌가....?
            // myStudioRepository.save(myStudio);

            // 지역부터 Location에 추가해준다.
            for(String s:info.getLocation()){
                Location loc=new Location(s);

                // 이미 있는 지역이 아니라면 location 테이블에 추가.
                if(locationRepository.findLocationByName(s)!=null){
                    locationRepository.save(loc);
                }
                // 들어온 지역 각각 저장한다... 이게 아니면 어떡하지....
                AuthorLocation authLoc=AuthorLocation.builder()
                                                .location(loc)
                                                .myStudio(myStudio)
                                                .build();
                authorLocationRepository.save(authLoc);
            }
        // 유저 만들어서 저장
        User user=User.builder()
                .id(info.getId())
                .nickname(info.getNickname())
                .passwd(info.getPasswd())
                .pg(info.getPg())
                .photo(info.getPhoto())
                .myStudio(myStudio)
                .build();
        userRepository.save(user);
        }
    }
}
