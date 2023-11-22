package com.devking.fefilm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "seriesId")
    private TvSeries series;
    @Column(nullable = false)
    private int episodeNum;
    @Column(nullable = false)
    private String episodeTitle;
    @Column(nullable = false)
    private String videoPath;
}
