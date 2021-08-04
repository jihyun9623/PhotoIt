package com.ssafy.api.controller;

import com.ssafy.api.request.SearchPageReq;
import com.ssafy.api.response.*;
import com.ssafy.api.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Api(value = "검색 api", tags = {"Search"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
    public static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    private final SearchService searchService;

    @GetMapping("/tag/{tag}/{location}")
    @ApiOperation(value = "해당 지역,태그 사진 가져오기", notes = "해당 지역의 사진ID, [썸네일, 닉네임]. 지역이 없으면 전지역(all)")
    public ResponseEntity<SearchTagLocationRes> searchTagLocation(@RequestBody @PathVariable("tag") String tag,
                                                                  @PathVariable("location") String location, SearchPageReq searchReq) {

        List<PhotoIdThumbNickNameRes> photoList = new ArrayList<>();        //사진ID, [사진, 닉네임]
        photoList = searchService.photoList(tag, location);
        return ResponseEntity.ok(SearchTagLocationRes.of(200,"Success", photoList, tag));
    }

    @GetMapping("/pg/{nickname}/{location}")
    @ApiOperation(value = "해당 지역, 작가 검색", notes = "해당 지역의 해당 닉네임을 포함하는 작가들의 프로필 사진을 가져온다. " +
                                                "지역이 없으면 전지역(all)")
    public ResponseEntity<SearchPgLocationRes> searchPg(@RequestBody @PathVariable("pg") String pg,
                                                        @PathVariable("nickname") String nickname,
                                                        @PathVariable("location") String location,  SearchPageReq searchReq) {
        List<ProfileNickNameRes> profileList = new ArrayList<>();      //프로필사진, 닉네임
        profileList = searchService.profileList(nickname, location);
        return ResponseEntity.ok(SearchPgLocationRes.of(200,"Success", profileList));
    }

    @GetMapping("/studiotag/{idx}/{tag}")
    @ApiOperation(value = "마이스튜디오 내 태그검색", notes = "현재 마이스튜디오에서 태그검색해서 그 스튜디오의 사진리스트 출력")
    public ResponseEntity<SearchMyStudioTagRes> searchMyStudioTag(@RequestBody @PathVariable("idx") int id,
                                                                  @PathVariable("tag") String tag, SearchPageReq searchReq) {

        List<PhotoIdPhotoRes> photoList = new ArrayList<>();       //원본사진, 사진ID
        photoList = searchService.myStudioPhotoList(tag, id);
        return ResponseEntity.ok(SearchMyStudioTagRes.of(200,"Success", photoList));
    }

}
