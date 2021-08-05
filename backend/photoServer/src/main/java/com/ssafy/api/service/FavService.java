/* 작성자 : 김지현 */

package com.ssafy.api.service;

import org.springframework.stereotype.Service;

@Service
public interface FavService {
    public boolean addFav(String userNick, String pgNick);

    public boolean deleteFav(String userNick, String pgNick);

    public boolean checkFav(String userNick, String pgNick);

    public Integer[] getFavList(String userNick);
}
