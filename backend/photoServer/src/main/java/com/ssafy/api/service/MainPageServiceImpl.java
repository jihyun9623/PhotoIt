package com.ssafy.api.service;

import com.ssafy.api.response.TagThumbNickNameRes;
import com.ssafy.api.response.ThumbNickNameRes;
import com.ssafy.api.response.ThumbPhotoIdRes;
import com.ssafy.api.response.UserProfile;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MainPageServiceImpl implements MainPageService{
    private final LocationRepository locationRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;
    private final MyStudioRepository myStudioRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    @Transactional
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
    @Transactional
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
    @Transactional
    public UserProfile userProfile(String JWT) {

        User user = userRepository.findUserById(jwtTokenUtil.getUserPk(JWT)).orElseThrow(RuntimeException::new);
        UserProfile userProfile = UserProfile.of(user.getNickname(), user.getPhoto());
        return userProfile;
    }

    @Override
    @Transactional
    public List<TagThumbNickNameRes> getMainContents() {
        int viewsTag = 3, randTag = 3, photoCnt = 20;  //view수 기반 태그, 랜덤 태그 수, 태그 별 사진 수
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

        List<TagThumbNickNameRes> tagPhotoList = new ArrayList<>();
        for(Tag t : tagList) {
            String tempTag = t.getName();
            List<ThumbNickNameRes> temp = new ArrayList<>();
            cnt = 0;
            for(Photo p : photos) {
                for(PhotoTag pt : p.getPhotoTags()) {
                    if(pt.getTag().getName() == tempTag) {
                        temp.add(ThumbNickNameRes.of(p.getThumbnail(), p.getIdx() ,p.getMyStudio().getNickname()));
                        cnt++;
                    }
                }
                if(cnt==photoCnt)
                    break;
            }
            tagPhotoList.add(TagThumbNickNameRes.of(tempTag, temp));
        }


        return tagPhotoList;
    }

    @Override
    @Transactional
    public String[] photoTagList(String thumbnail) {
        Photo photo = photoRepository.findByThumbnail(thumbnail)
                    .orElseThrow(RuntimeException::new);

        List<PhotoTag> photoTags = photo.getPhotoTags();
        String[] photoTagList = new String[photoTags.size()];
        int cnt = 0;
        for(PhotoTag pt: photoTags) {
            photoTagList[cnt++] = pt.getTag().getName();
        }
        return photoTagList;
    }

    @Override
    @Transactional
    public String photoOrigin(String thumbnail) {
        Photo photo = photoRepository.findByThumbnail(thumbnail)
                .orElseThrow(RuntimeException::new);

        return photo.getOrigin();
    }

    @Override
    @Transactional
    public boolean isFavorite(String nickName, String userId) {
        Boolean isFav = false;
        MyStudio myStudio = myStudioRepository.findByNickname(nickName)
                            .orElseThrow(RuntimeException::new);
        if(userId=="")
            return false;
        User user = userRepository.findUserById(userId)
                    .orElseThrow(RuntimeException::new);
        for(Favorite f : user.getFavorites()) {
            if(f.getMyStudio().getNickname() == nickName) {
                isFav = true;
                break;
            }
        }
        return isFav;
    }

    @Override
    @Transactional
    public List<ThumbPhotoIdRes> thumbPhotoIds(String nickName, String thumbnail) {
        int thumbPhotoIdsSize = 20;
        MyStudio myStudio = myStudioRepository.findByNickname(nickName)
                            .orElseThrow(RuntimeException::new);
        List<ThumbPhotoIdRes> thumbPhotoIds = new ArrayList<>();
        for(Photo p : myStudio.getPhotos()) {
            if(p.getThumbnail()==thumbnail)
                continue;
            ThumbPhotoIdRes temp = ThumbPhotoIdRes.of(p.getThumbnail(), p.getIdx());
            thumbPhotoIds.add(temp);
            if(thumbPhotoIds.size() == thumbPhotoIdsSize)
                break;
        }
        return thumbPhotoIds;
    }

    @Override
    @Transactional
    public void photoViewCnt(String thumbnail) {
        Photo photo = photoRepository.findByThumbnail(thumbnail)
                        .orElseThrow(RuntimeException::new);

        Photo newPhoto = Photo.builder()
                         .idx(photo.getIdx())
                         .best(photo.isBest())
                         .viewCnt(photo.getViewCnt()+1)
                         .myStudio(photo.getMyStudio())
                         .origin(photo.getOrigin())
                         .thumbnail(photo.getThumbnail())
                         .upload(photo.getUpload())
                         .build();

        photoRepository.save(newPhoto);

    }
}
