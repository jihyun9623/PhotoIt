/* 작성자 : 김지현 */

package com.ssafy.api.service;

import com.ssafy.db.entity.Favorite;
import org.springframework.stereotype.Service;

@Service
public interface FavService {
    Favorite favorite(Integer userIdx);

    public boolean toggleFav(String nickname);

    public boolean checkFav(String nickname);

    public boolean getFavList(String nickname);
}
