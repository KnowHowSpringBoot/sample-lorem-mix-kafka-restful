package dev.knowhowto.lorem.history.repository;

import java.util.List;

import dev.knowhowto.lorem.history.entity.Report;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ReportRepository
    extends PagingAndSortingRepository<Report, Long>, JpaRepository<Report, Long> {
  @Transactional(readOnly = true)
  List<Report> findAllByOrderByIdDesc(Pageable pageRequest);
}
