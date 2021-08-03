package com.ssafy.service;

import com.ssafy.api.service.MainPageService;
import com.ssafy.db.entity.Tag;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.LocationRepository;
import com.ssafy.db.repository.TagRepository;
import com.ssafy.db.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

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
        User user = User.builder()
                    .id("hanq@kakao.com")
                    .passwd("qwe123")
                    .nickname("hanq")
                    .pg(false)
                    .photo("dadw")
                    .build();
        userRepository.save(user);
        assertEquals(user, mainPageService.getUser("Asda", "hanq@kakao.com"));

    }

    @Test
    void getMainContents() {
    }
}