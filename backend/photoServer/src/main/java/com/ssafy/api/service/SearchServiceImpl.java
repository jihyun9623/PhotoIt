package com.ssafy.api.service;

import com.ssafy.api.response.PhotoIdPhotoRes;
import com.ssafy.api.response.PhotoIdThumbNickNameRes;
import com.ssafy.api.response.ProfileNickNameRes;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService{
    private final PhotoRepository photoRepository;
    private final MyStudioRepository myStudioRepository;

    @Override
    public List<PhotoIdThumbNickNameRes> photoList(String tag, String location) {
        List<PhotoIdThumbNickNameRes> photoList = new ArrayList<>();  //사진ID, [썸네일, 닉네임]
        List<Photo> photos = photoRepository.findAll();
        for(Photo p : photos) {
            for(AuthorLocation al : p.getMyStudio().getAuthorLocations()) {
                if (tag == al.getLocation().getName()) {
                    photoList.add(PhotoIdThumbNickNameRes.of(p.getIdx(), p.getThumbnail(), p.getMyStudio().getNickname()));
                }

            }
        }

        return photoList;
    }

    @Override
    public List<ProfileNickNameRes> profileList(String nickname, String location) {
        List<ProfileNickNameRes> profileList = new ArrayList<>();  //프로필사진, 닉네임
        List<MyStudio> myStudios = myStudioRepository.findByNicknameContaining(nickname);
        if(location == "all") {
            for(MyStudio ms : myStudios) {
                profileList.add(ProfileNickNameRes.of(ms.getUser().getPhoto(), ms.getNickname()));
            }
        }
        else {
            for(MyStudio ms : myStudios) {
                for(AuthorLocation al : ms.getAuthorLocations()) {
                    if(al.getLocation().getName() == location)
                        profileList.add(ProfileNickNameRes.of(ms.getUser().getPhoto(), ms.getNickname()));
                }
            }
        }

        return profileList;
    }

    @Override
    public List<PhotoIdPhotoRes> myStudioPhotoList(String tag, Integer id) {
        List<PhotoIdPhotoRes> retPhotoList = new ArrayList<>();   //원본사진, 사진ID
        List<Photo> photos = myStudioRepository.findById(id)
                .orElseThrow(RuntimeException::new).getPhotos();

        for(Photo p : photos) {
            for(PhotoTag pt : p.getPhotoTags()) {
                if(tag == pt.getTag().getName())
                    retPhotoList.add(PhotoIdPhotoRes.of(p.getIdx(), p.getOrigin()));
            }
        }
        return retPhotoList;
    }
}
