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
import java.util.stream.Stream;

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
        User user = userRepository.findUserById(jwtTokenUtil.getUserInfo(JWT)).orElseThrow(RuntimeException::new);
        UserProfile userProfile = UserProfile.of(user.getNickname(), user.getPhoto());
        return userProfile;
    }

    @Override
    @Transactional
    public List<TagThumbNickNameRes> getMainContents() {
        int viewsTag = 2, randTag = 2, photoCnt = 20;  //view수 기반 태그, 랜덤 태그 수, 태그 별 사진 수
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
            for(int i=0; i<tagList.size(); ++i) {
                if(tagList.get(i).getName().equals(tag.getName())) {
                    tagList.remove(i);
                    break;
                }
            }
            if(cnt==viewsTag)
                break;
        }
        for(int i=0; i<randTag; ++i) {
            int tempIdx = (int) (Math.random() * tagList.size());
            mainTags.add(tagList.get(tempIdx));
            tagList.remove(tempIdx);
        }

        // tag viewTags + randTag 만큼 넘겨주기!
        int cnt_=0;
        List<TagThumbNickNameRes> tagPhotoList = new ArrayList<>();
        for(Tag t : mainTags) {
            String tempTag = t.getName();
            List<ThumbNickNameRes> temp = new ArrayList<>();
            cnt = 0;
            for(Photo p : photos) {
                for(PhotoTag pt : p.getPhotoTags()) {
                    if(pt.getTag().getName().equals(tempTag)) {
                        temp.add(ThumbNickNameRes.of(p.getThumbnail(), p.getIdx() ,p.getMyStudio().getNickname()));
                        cnt++;
                    }
                }
                if(cnt==photoCnt)
                    break;
            }
            tagPhotoList.add(TagThumbNickNameRes.of(tempTag, temp));
            if(++cnt_==viewsTag+randTag)
                break;
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
        
        if(userId=="")
            return false;
        User user = userRepository.findUserById(userId)
                    .orElseThrow(RuntimeException::new);
        for(Favorite f : user.getFavorites()) {
            if(f.getMyStudio().getNickname().equals(nickName)) {
                isFav = true;
                break;
            }
        }
        return isFav;
    }

    @Override
    @Transactional
    public List<ThumbPhotoIdRes> thumbPhotoIds(String nickName, String thumbnail) {
        int thumbPhotoIdsSize = 4;
        MyStudio myStudio = myStudioRepository.findByNickname(nickName)
                            .orElseThrow(RuntimeException::new);
        List<ThumbPhotoIdRes> thumbPhotoIds = new ArrayList<>();
        Stream<Photo> photos = myStudio.getPhotos().stream().sorted(new Comparator<Photo>() {
            @Override
            public int compare(Photo o1, Photo o2) {
                return o2.getIdx() - o1.getIdx();
            }
        });
        photos.limit(5).forEach(p ->{
            ThumbPhotoIdRes temp = ThumbPhotoIdRes.of(p.getThumbnail(), p.getIdx());
            if(p.getThumbnail()!=thumbnail)
                thumbPhotoIds.add(temp);
        });
        if(thumbPhotoIds.size()==5)
            thumbPhotoIds.remove(4);
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

    @Override
    @Transactional
    public int photoIdx(String thumbnail) {
        int photoIdx = photoRepository.findByThumbnail(thumbnail)
                       .orElseThrow(RuntimeException::new).getIdx();

        return photoIdx;
    }

    @Override
    @Transactional
    public String profilePhoto(String thumbnail) {
        String profilePhoto = photoRepository.findByThumbnail(thumbnail)
                        .orElseThrow(RuntimeException::new).getMyStudio().getUser().getPhoto();
        return profilePhoto;
    }
}
