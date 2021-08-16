package com.ssafy.api.service;

import com.ssafy.api.response.PhotoIdPhotoRes;
import com.ssafy.api.response.PhotoIdThumbNickNameRes;
import com.ssafy.api.response.ProfileNickNameRes;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService{
    private final PhotoRepository photoRepository;
    private final MyStudioRepository myStudioRepository;

    @Override
    @Transactional
    public List<PhotoIdThumbNickNameRes> photoList(String tag, String location) {
        List<PhotoIdThumbNickNameRes> photoList = new ArrayList<>();  //사진ID, [썸네일, 닉네임]
        List<Photo> photos = photoRepository.findAll();
        if(location.equals("all")) {
            for(Photo p : photos) {
                for(PhotoTag pt : p.getPhotoTags()) {
                    if(pt.getTag().getName().equals(tag)) {
                        photoList.add(PhotoIdThumbNickNameRes.of(p.getIdx(), p.getThumbnail(), p.getMyStudio().getNickname()));
                    }
                }
            }
        }
        else {
            for(Photo p : photos) {
                for(AuthorLocation al : p.getMyStudio().getAuthorLocations()) {
                    if (location.equals(al.getLocation().getName()) ) {
                        for(PhotoTag pt : p.getPhotoTags()) {
                            if(pt.getTag().getName().equals(tag)) {
                                photoList.add(PhotoIdThumbNickNameRes.of(p.getIdx(), p.getThumbnail(), p.getMyStudio().getNickname()));
                            }
                            break;
                        }
                        break;
                    }

                }
            }
        }

        return photoList;
    }

    @Override
    @Transactional
    public List<ProfileNickNameRes> profileList(String nickname, String location) {
        List<ProfileNickNameRes> profileList = new ArrayList<>();  //프로필사진, 닉네임
        List<MyStudio> myStudios = myStudioRepository.findByNicknameContaining(nickname);
        if(location.equals("all")) {
            for(MyStudio ms : myStudios) {
                profileList.add(ProfileNickNameRes.of(ms.getUser().getPhoto(), ms.getNickname()));
            }
        }
        else {
            for(MyStudio ms : myStudios) {
                for(AuthorLocation al : ms.getAuthorLocations()) {
                    if(al.getLocation().getName().equals(location))
                        profileList.add(ProfileNickNameRes.of(ms.getUser().getPhoto(), ms.getNickname()));
                }
            }
        }

        return profileList;
    }


    @Override
    @Transactional
    public List<PhotoIdPhotoRes> myStudioPhotoList(String tag, String nickName) {
        List<PhotoIdPhotoRes> retPhotoList = new ArrayList<>();   //원본사진, 사진ID
        List<Photo> photos = myStudioRepository.findByNickname(nickName)
                .orElseThrow(RuntimeException::new).getPhotos();

        for(Photo p : photos) {
            for(PhotoTag pt : p.getPhotoTags()) {
                if(tag.equals(pt.getTag().getName()))
                    retPhotoList.add(PhotoIdPhotoRes.of(p.getIdx(), p.getOrigin()));
            }
        }
        return retPhotoList;
    }
}
