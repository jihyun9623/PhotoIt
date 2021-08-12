package com.ssafy.service;

import com.ssafy.api.service.MainPageService;
import com.ssafy.db.entity.MyStudio;
import com.ssafy.db.entity.Tag;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.LocationRepository;
import com.ssafy.db.repository.MyStudioRepository;
import com.ssafy.db.repository.TagRepository;
import com.ssafy.db.repository.UserRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MainPageServiceImplTest {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    MyStudioRepository myStudioRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MainPageService mainPageService;


    @Test
    void 지역리스트() throws Exception{
        String[] a = new String[2];
        a[0] = "서울";
        a[1] = "부산";

        String[] b = mainPageService.locationList();

        Arrays.sort(a);
        Arrays.sort(b);
        assertTrue(Arrays.deepEquals(a,b));

    }

    @Test
    void 태그목록() {
        Tag tag1 = Tag.builder()
                .name("야외")
                .build();
        Tag tag2 = Tag.builder()
                .name("웨딩")
                .build();

        tagRepository.save(tag1);
        tagRepository.save(tag2);

        String[] a = new String[2];
        a[0] = "야외";
        a[1] = "웨딩";

        String[] b = mainPageService.tagList();

        Arrays.sort(a);
        Arrays.sort(b);
        assertTrue(Arrays.deepEquals(a,b));
    }

    @Test
    void 유저들고오기() {

    }

    @Test
    void detail() {

    }
}