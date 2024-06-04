package com.minsih.chronoman.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minsih.chronoman.model.Site;
import com.minsih.chronoman.service.SiteService;

@RestController
@RequestMapping("/api/sites")
public class SiteController {

    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    /*
     * Notice that pages start from 0 in the backend
     */
    @GetMapping
    public ResponseEntity<List<Site>> getAllSites(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Site> sites;
        if (!(name == null || name.isBlank())) {
            sites = siteService.search(name, pageable);
        } else {
            sites = siteService.findAll(pageable);
        }
        return new ResponseEntity<>(sites.getContent(), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getSiteCount() {
        Long count = siteService.count();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Site> getSiteById(@PathVariable Long id) {
        Site site = siteService.findById(id);
        return site != null ? new ResponseEntity<>(site, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Site> createSite(@RequestBody Site site) {
        Site createdSite = siteService.save(site);
        System.out.println(createdSite);

        return new ResponseEntity<>(createdSite, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Site> updateSite(@PathVariable Long id, @RequestBody Site site) {
        Site existingSite = siteService.findById(id);
        if (existingSite != null) {
            site.setId(id);
            Site updatedSite = siteService.save(site);
            return new ResponseEntity<>(updatedSite, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSite(@PathVariable Long id) {
        Site site = siteService.findById(id);
        if (site != null) {
            siteService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
