package com.unbeaten.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.unbeaten.wiki.domain.Doc;
import com.unbeaten.wiki.mapper.DocMapper;
import com.unbeaten.wiki.req.DocQueryReq;
import com.unbeaten.wiki.req.DocSaveReq;
import com.unbeaten.wiki.resp.DocQueryResp;
import com.unbeaten.wiki.resp.PageResp;
import com.unbeaten.wiki.service.IDocService;
import com.unbeaten.wiki.util.CopyUtil;
import com.unbeaten.wiki.util.SnowFlake;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 文档 服务实现类
 * </p>
 *
 * @author unbeaten
 * @since 2021-06-29
 */
@Service
public class DocServiceImpl extends ServiceImpl<DocMapper, Doc> implements IDocService {

    @Resource
    private DocMapper docMapper;

    @Resource
    private SnowFlake snowFlake;

    private static final Logger LOG = LoggerFactory.getLogger(DocServiceImpl.class);

    @Override
    public List<DocQueryResp> all() {
        QueryWrapper<Doc> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        List<Doc> docList = docMapper.selectList(queryWrapper);
        List<DocQueryResp> list = CopyUtil.copyList(docList, DocQueryResp.class);
        return list;
    }

    @Override
    public PageResp<DocQueryResp> list(DocQueryReq req) {
        QueryWrapper<Doc> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Doc> docList = docMapper.selectList(queryWrapper);


        PageInfo<Doc> pageInfo = new PageInfo<>(docList);
        LOG.info("总行数：{}",pageInfo.getTotal());
        LOG.info("总页数：{}",pageInfo.getPages());
//        List<DocQueryResp> respList = new ArrayList<>();
//        for (Doc doc : docList) {
//            DocQueryResp docResp = new DocQueryResp();
//            BeanUtils.copyProperties(doc,docResp);
//            respList.add(docResp);
//        }

        List<DocQueryResp> list = CopyUtil.copyList(docList, DocQueryResp.class);
        PageResp<DocQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

    @Override
    public void save(DocSaveReq req) {
        Doc doc= CopyUtil.copy(req,Doc.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            //新增
            doc.setId(snowFlake.nextId());
            docMapper.insert(doc);
        } else {
            //更新
            docMapper.updateById(doc);
        }
    }

    @Override
    public void delete(Long id) {
        docMapper.deleteById(id);
    }

    public void delete(List<String> ids) {
        docMapper.deleteBatchIds(ids);
    }
    
}
