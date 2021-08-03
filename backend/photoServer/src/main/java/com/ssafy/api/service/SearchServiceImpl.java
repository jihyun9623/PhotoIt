package com.ssafy.api.service;

import com.ssafy.db.entity.*;
import com.ssafy.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService{
    private final PhotoRepository photoRepository;
    private final MyStudioRepository myStudioRepository;

    @Override
    public Map<Integer, String[]> photoList(String tag, String location) {
        Map<Integer, String[]> photoList = new TreeMap<>();  //사진ID, [썸네일, 닉네임]
        List<Photo> photos = photoRepository.findAll();
        for(Photo p : photos) {
            for(AuthorLocation al : p.getMyStudio().getAuthorLocations()) {
                if(tag == al.getLocation().getName()) {
                    String[] thumNick = new String[2];
                    thumNick[0] = p.getThumbnail();
                    thumNick[1] = p.getMyStudio().getNickname();
                    photoList.put(p.getIdx(), thumNick);
                }

            }
        }

        return photoList;
    }

    @Override
    public Map<String, String> profileList(String nickname, String location) {
        Map<String, String> profileList = new TreeMap<>();  //프로필사진, 닉네임
        List<MyStudio> myStudios = myStudioRepository.findByNicknameContaining(nickname);
        if(location == "all") {
            for(MyStudio ms : myStudios) {
                profileList.put(ms.getProfile(), ms.getNickname());
            }
        }
        else {
            for(MyStudio ms : myStudios) {
                for(AuthorLocation al : ms.getAuthorLocations()) {
                    if(al.getLocation().getName() == location)
                        profileList.put(ms.getProfile(), ms.getNickname());
                }
            }
        }

        return profileList;
    }

    @Override
    public Map<String, Integer> myStudioPhotoList(String tag, Integer id) {
        Map<String, Integer> retPhotoList = new TreeMap<>();   //원본사진, 사진ID
        List<Photo> photos = myStudioRepository.findById(id)
                .orElseThrow(RuntimeException::new).getPhotos();

        for(Photo p : photos) {
            for(PhotoTag pt : p.getPhotoTags()) {
                if(tag == pt.getTag().getName())
                    retPhotoList.put(p.getOrigin(), p.getIdx());
            }
        }
        return retPhotoList;
    }
}
