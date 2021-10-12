package com.example.F1API.service;

import com.example.F1API.model.Driver;
import com.example.F1API.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService implements DriverRepository {

    @Autowired
    DriverRepository driverRepository;

    @Override
    public List<Driver> findAll() {
        return null;
    }

    @Override
    public List<Driver> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Driver> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Driver> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Driver entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Driver> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Driver> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Driver> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Driver> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Driver> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Driver> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Driver> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Driver getOne(Long aLong) {
        return null;
    }

    @Override
    public Driver getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Driver> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Driver> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Driver> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Driver> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Driver> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Driver> boolean exists(Example<S> example) {
        return false;
    }
}
