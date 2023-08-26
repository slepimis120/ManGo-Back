package com.mango.mapper;

import com.mango.dto.ReviewDTO;
import com.mango.model.Review;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ReviewDTOMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public ReviewDTOMapper(ModelMapper modelMapper) {
        ReviewDTOMapper.modelMapper = modelMapper;
    }
    public static Review fromDTOtoReview(ReviewDTO dto) {
        return modelMapper.map(dto, Review.class);
    }

    public static ReviewDTO fromReviewtoDTO(Review dto) {
        return modelMapper.map(dto, ReviewDTO.class);
    }

}
