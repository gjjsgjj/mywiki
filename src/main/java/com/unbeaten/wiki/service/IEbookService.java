package com.unbeaten.wiki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.unbeaten.wiki.domain.Ebook;
import com.unbeaten.wiki.req.EbookQueryReq;
import com.unbeaten.wiki.req.EbookSaveReq;
import com.unbeaten.wiki.resp.EbookQueryResp;
import com.unbeaten.wiki.resp.PageResp;

/**
 * <p>
 * 电子书 服务类
 * </p>
 *
 * @author unbeaten
 * @since 2021-06-25
 */
public interface IEbookService extends IService<Ebook> {
    PageResp<EbookQueryResp> list(EbookQueryReq req);
    //保存
    void save(EbookSaveReq req);

    void delete(Long id);

}
