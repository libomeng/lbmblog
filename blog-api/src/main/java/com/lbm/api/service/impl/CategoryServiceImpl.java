package com.lbm.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbm.api.config.RedisKeyConfig;
import com.lbm.api.dao.entity.Category;
import com.lbm.api.dao.mapper.CategoryMapper;
import com.lbm.api.service.CategoryService;
import com.lbm.api.service.RedisService;
import com.lbm.api.vo.CategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper,Category> implements CategoryService {


    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    RedisService redisService;

    /**
     * 根据标签ID查找标签
     * 返回多个标签功能（未实现）
     * 此处以list包装数据，但list中只有一个数据
     * @param categoryId
     * @return
     */
    @Override
    public CategoryVo findCategoryById(String categoryId) {
        //从Redis中获取数据
       String key = RedisKeyConfig.CATEGORY_VO;
        CategoryVo entity = redisService.getEntity(key, categoryId, CategoryVo.class);
        if(entity != null){
            return  entity;
        }
        Category category = categoryMapper.selectById(categoryId);
        CategoryVo categoryVo =new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        redisService.setEntity(key,categoryId,categoryVo);
        return categoryVo;
    }

    @Override
    public List<Category> findAllCategorys(){
        //从redis中获取数据
        String key = RedisKeyConfig.CATEGORY_LIST;
        List<Category> entity = redisService.getEntity(key, List.class);
        if(entity !=null){
            return entity;
        }
        LambdaQueryWrapper<Category> queryWrapper =new LambdaQueryWrapper();
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        redisService.setEntity(key,categories);
        return categories;
    }
    @Override
    public Category findDetailCategoryById(String id) {
        Category category = categoryMapper.selectById(id);
        return category;
    }


}