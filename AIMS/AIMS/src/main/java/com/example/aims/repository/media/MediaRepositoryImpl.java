package com.example.aims.repository.media;

import com.example.aims.entity.media.Book;
import com.example.aims.entity.media.CD;
import com.example.aims.entity.media.DVD;
import com.example.aims.entity.media.Media;
import com.example.aims.repository.AIMSDB;
import com.example.aims.response.MediaResponse;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


public class MediaRepositoryImpl implements MediaRepository {
    public MediaRepositoryImpl() {

    }

    @Override
    public MediaResponse getMediaListByFilter(String query, String priceFilter, int pageCount) {
        TypedQuery<Media> tp = buildQueryFrom(query, priceFilter);
        List<Media> mediaList = tp.getResultList();
        int mediaCount = mediaList.size();
        MediaResponse mediaResponse = MediaResponse
                .builder()
                .mediaList(tp.setMaxResults(20).setFirstResult(pageCount * 20).getResultList())
                .mediaCount(mediaCount)
                .build();

        return mediaResponse;
    }

    @Override
    public Book getBookById(int id) {
        EntityManager em = AIMSDB.getEntityManager();
        return em.find(Book.class, id);

    }

    @Override
    public DVD getDVDById(int id) {
        EntityManager em = AIMSDB.getEntityManager();
        return em.find(DVD.class, id);
    }

    @Override
    public CD getCDById(int id) {
        EntityManager em = AIMSDB.getEntityManager();
        return em.find(CD.class, id);
    }


    private boolean isQueryCategory(String query) {
        if (query.trim().equalsIgnoreCase("DVD"))
            return true;
        if (query.trim().equalsIgnoreCase("CD"))
            return true;
        return query.trim().equalsIgnoreCase("BOOK");
    }

    public TypedQuery<Media> buildQueryFrom(String query, String priceFilter) {
        EntityManager em = AIMSDB.getEntityManager();
        TypedQuery<Media> q;
        StringBuilder jqpl = new StringBuilder("select m from Media m ");
        if (!query.isEmpty()) {
            if (isQueryCategory(query))
                jqpl.append(" where m.category = :query");
            else {
                jqpl.append(" where m.title like :query ");
                query = "%" + query + "%";
            }
        }
        if (!priceFilter.isEmpty()) {
            if (priceFilter.equals("High to Low"))
                jqpl.append(" order by m.price desc ");
            else if (priceFilter.equals("Low to High"))
                jqpl.append(" order by m.price asc ");
        }
        q = em.createQuery(jqpl.toString(), Media.class);
        if (!query.isEmpty()) {
            q.setParameter("query", query);
        }
        return q;
    }
}
