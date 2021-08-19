/* 2021-08-02 스켈레톤 코드 작성	by.HeeJung,Lee */

package com.ssafy.api.service;

import com.ssafy.api.response.StudioEditPgProfileResponseBody;
import com.ssafy.api.response.StudioEditPhotoResponseBody;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@Service
public interface StudioEditService {

    /**
     * @param JWT
     * @param nickname
     * @return
     * true : same user
     * false : not same user
     */
    boolean studioAuth(String JWT, String nickname);

    /**
     * @param JWT
     * @return
     * null : 작가가 아니거나 지역을 선택하지않아 지역이 없음
     */
    StudioEditPgProfileResponseBody getPgProfile(String JWT);

    /**
     * @param JWT
     * @return
     * null : 등록된 베스트 사진없음
     */
    StudioEditPhotoResponseBody getBestPhoto(String JWT);

    /**
     * @param JWT
     * @return
     * null : 등록된 사진없음
     */
    StudioEditPhotoResponseBody getPgPhoto(String JWT);

    /**
     * @param JWT
     * @param add_id
     * @return
     * False : 베스트 사진 적용 실패
     */
    boolean addBestPhoto(String JWT, int add_id);

    /**
     * @param JWT
     * @param add_id
     * @param del_id
     * @return
     * False : 베스트 사진 수정 실패
     */
    boolean updateBestPhoto(String JWT, int add_id, int del_id);

    /**
     * @param JWT
     * @param del_id
     * @return
     * False : 베스트 사진 삭제 실패
     */
    boolean delBestPhoto(String JWT, int del_id);

    /**
     * @param JWT
     * @param files
     * @param tags
     * @return
     * False : 업로드 실패
     */
    boolean addPgPhoto(String JWT, MultipartHttpServletRequest request, String[] tags);

    /**
     * @param JWT
     * @param del_id
     * @return
     * False : 삭제 실패
     */
    boolean delPgPhoto(String JWT, int del_id);
}
