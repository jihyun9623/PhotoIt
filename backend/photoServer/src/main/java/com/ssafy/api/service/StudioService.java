/* 작성자 : 김지현 */

package com.ssafy.api.service;

import com.ssafy.api.response.StudioGetPhotosResBody;
import com.ssafy.api.response.StudioPgProfileResBody;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface StudioService {
    public Integer getStudioIdx(String nickname);

    public StudioPgProfileResBody getPgProfile(String nickname);

    public LocalDateTime[] showCalendar(String nickname);

    public boolean editCalendar(String nickname, String JWT, LocalDateTime[] cal_time);

    public StudioGetPhotosResBody getBestPhotos(String nickname);

    public StudioGetPhotosResBody getAllPgPhotos(String nickname);
}
