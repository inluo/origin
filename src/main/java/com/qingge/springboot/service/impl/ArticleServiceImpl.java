package com.qingge.springboot.service.impl;

import com.qingge.springboot.entity.Article;
import com.qingge.springboot.mapper.ArticleMapper;
import com.qingge.springboot.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  文章服务实现
 * </p>
 *
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
