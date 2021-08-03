package com.ssafy.api.service;

import com.ssafy.db.entity.*;
import com.ssafy.db.repository.LocationRepository;
import com.ssafy.db.repository.PhotoRepository;
import com.ssafy.db.repository.TagRepository;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MainPageServiceImpl implements MainPageService{
    private final LocationRepository locationRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;

    @Override
    public String[] locationList() {
        List<Location> locations = locationRepository.findAll();
        String[] locationList = new String[locations.size()];
        int i=0;
        for(Location loc : locations) {
            locationList[i++] = loc.getName();
        }
        return locationList;
    }

    @Override
    public String[] tagList() {
        List<Tag> tags = tagRepository.findAll();
        String[] tagList = new String[tags.size()];
        int i=0;
        for(Tag tag : tags) {
            tagList[i++] = tag.getName();
        }
        return tagList;
    }

    @Override
    public User getUser(String JWT, String id) {
        if(JWT==null)
            return null;
        return userRepository.findUserById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Map<String, Map<String, String>> getMainContents() {
        int viewsTag = 3, randTag = 3;  //view수 기반 태그, 랜덤 태그 수
        List<Tag> mainTags = new ArrayList<>();
        List<Photo> photos = photoRepository.findAll();
        List<Tag> tagList = tagRepository.findAll();
        Map<String, Integer> tagViewCnt = new HashMap<>();

        for(Tag t : tagList) {
            tagViewCnt.put(t.getName(), 0);
        }

        for(Photo p : photos) {
            for(PhotoTag pt : p.getPhotoTags()) {
                int temp = tagViewCnt.get(pt.getTag().getName());
                tagViewCnt.put(pt.getTag().getName(), temp + p.getViewCnt());
            }
        }

        // Map.Entry 리스트 작성
        List<Map.Entry<String, Integer>> list_entries = new ArrayList<>(tagViewCnt.entrySet());

        // 비교함수 Comparator를 사용하여 내림 차순으로 정렬
        // compare로 값을 비교
        Collections.sort(list_entries, (obj1, obj2) -> {
            // 내림 차순으로 정렬
            return obj2.getValue().compareTo(obj1.getValue());
        });
        int cnt = 0;
        for(Map.Entry<String, Integer> entry : list_entries) {
            cnt++;
            Tag tag = Tag.builder()
                    .name(entry.getKey())
                    .build();
            mainTags.add(tag);
            tagList.remove(tag);
            System.out.println(entry.getKey() + " : " + entry.getValue());
            if(cnt==viewsTag)
                break;
        }
        for(int i=0; i<randTag; ++i) {
            int tempIdx = (int) (Math.random() * tagList.size());
            mainTags.add(tagList.get(tempIdx));
            tagList.remove(tempIdx);
        }

        // tag viewTags + randTag 만큼 넘겨주기!

        Map<String, Map<String, String>> tagPhotoList = new HashMap();
        for(Tag t : tagList) {
            String tempTag = t.getName();
            Map<String, String> tempMap = new HashMap<>();

            for(Photo p : photos) {
                for(PhotoTag pt : p.getPhotoTags()) {
                    if(pt.getTag().getName() == tempTag) {
                        tempMap.put(p.getOrigin(), p.getMyStudio().getNickname());
                    }
                }
            }
            tagPhotoList.put(tempTag, tempMap);
        }

        return tagPhotoList;
    }
}
