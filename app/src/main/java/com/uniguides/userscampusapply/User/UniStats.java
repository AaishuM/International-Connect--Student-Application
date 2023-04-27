package com.uniguides.userscampusapply.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.uniguides.userscampusapply.R;

public class UniStats extends AppCompatActivity {

    private ListView listView;

    private String[] universities = {"Iowa State University", "Chapman University", "University Of California", "California State University", "University of Pennsylvania"};
    private String[] descriptions = {"Iowa University Academics \n"+"Allowed GPA : 6.8 \n"+"Iowa University Application Processing Period: Between 20 to 25 days  \n"+"Iowa University History \n"+"Iowa State University (ISU) is a public land-grant and space-grant research university located in Ames, Iowa, USA. It was founded in 1858 as Iowa Agricultural College and Model Farm, making it the nation's first land-grant institution, authorized under the Morrill Act of 1862.\n" +
            "\n" +
            "Initially, ISU's focus was on agricultural and mechanical arts education, but over time, it has grown to encompass a wide range of academic programs across various fields, including engineering, science, business, education, and liberal arts.\n" +
            "\n" +
            "During the late 19th and early 20th centuries, ISU played a crucial role in developing Iowa's agricultural economy, through research and extension services to farmers. The university has also contributed to scientific discoveries and advancements in fields such as nuclear physics, biotechnology, and food science.\n" +
            "\n" +
            "ISU has a strong athletic program, with its football team, the Cyclones, competing in the Big 12 Conference. The university also has a thriving student life, with over 850 student organizations, including clubs for various sports, academic interests, and cultural backgrounds.\n" +
            "\n" +
            "Today, Iowa State University is one of the largest universities in Iowa, with a student population of over 34,000 undergraduate and graduate students. It is consistently ranked among the top public universities in the United States for its academic programs, research opportunities, and student outcomes.", "Chapman University is a private university located in Orange, California, USA. It was founded in 1861 by the Christian Church (Disciples of Christ) and named Hesperian College, which was later renamed to Chapman College in honor of Charles C. Chapman, a successful businessman and philanthropist who provided financial support to the institution. In 1991, Chapman College officially became Chapman University.\n" +
            "\n" +
            "Originally, Chapman University was a small liberal arts college, but over time it has expanded to offer a wide range of academic programs across various fields, including business, education, film, law, medicine, and science. It is known for its strong programs in film and media arts, with its Dodge College of Film and Media Arts consistently ranked among the top film schools in the United States.\n" +
            "\n" +
            "Throughout its history, Chapman University has undergone several changes and developments, including the addition of new buildings and campuses. In 1954, the university moved to its current location in Orange, California, where it has since grown to encompass a 78-acre campus with over 11,000 students enrolled.\n" +
            "\n" +
            "Chapman University is also known for its commitment to diversity and inclusion, with initiatives and programs aimed at creating a welcoming and inclusive environment for all students. In recent years, the university has been recognized for its efforts in sustainability, earning several awards for its green initiatives and environmental stewardship.\n" +
            "\n" +
            "Today, Chapman University is a leading private university in California, with a focus on providing a personalized and interdisciplinary education to its students. It has a strong reputation for academic excellence, research, and community engagement, with a vibrant student life and a wide range of extracurricular activities and programs.", "The University of California (UC) is a public research university system in the state of California, USA. It was founded in 1868 in Oakland, California, and is now headquartered in Oakland and has ten campuses throughout the state.\n" +
            "\n" +
            "The idea for the University of California was first proposed in 1853 by the state's first superintendent of public instruction, John Swett. The goal was to create a system of higher education that would provide affordable and accessible education to all Californians, regardless of their social and economic backgrounds. However, it was not until 1868 that the university was officially established with the signing of the Organic Act by Governor Henry H. Haight.\n" +
            "\n" +
            "The first campus of the University of California was established in Berkeley, California, in 1868, and it quickly became the flagship campus of the university system. Over time, the university system grew, with the addition of several new campuses, including UCLA, UC San Francisco, UC San Diego, UC Davis, UC Santa Barbara, UC Santa Cruz, UC Irvine, UC Riverside, and UC Merced.\n" +
            "\n" +
            "The University of California has a strong reputation for academic excellence, research, and innovation. It has produced numerous Nobel laureates, Pulitzer Prize winners, and MacArthur fellows, and has been consistently ranked as one of the top public universities in the United States.\n" +
            "\n" +
            "Throughout its history, the University of California has also played an important role in shaping the state and nation, with a focus on advancing knowledge, promoting social justice, and contributing to the common good. It has been at the forefront of many important social and political movements, including the civil rights movement, the anti-war movement, and the environmental movement.\n" +
            "\n" +
            "Today, the University of California is one of the largest and most prestigious university systems in the world, with a diverse student body, world-class faculty, and a commitment to excellence in teaching, research, and service.", "California State University, Los Angeles (Cal State LA) is a public university located in Los Angeles, California, USA. It was founded in 1947 as the Los Angeles State College of Applied Arts and Sciences, and it became a member of the California State University system in 1963.\n" +
            "\n" +
            "Cal State LA has a long history of providing accessible and affordable education to the diverse communities of Los Angeles and the surrounding region. Originally, the university focused on vocational and technical education, but over time it has expanded to offer a wide range of academic programs across various fields, including business, education, engineering, liberal arts, and natural sciences.\n" +
            "\n" +
            "In recent years, Cal State LA has undergone several changes and developments, including the addition of new buildings and facilities, and a renewed emphasis on research and community engagement. The university is known for its commitment to social justice and community service, with initiatives and programs aimed at promoting equity, diversity, and inclusion.\n" +
            "\n" +
            "Cal State LA has a diverse student body, with over 28,000 students enrolled, and a dedicated faculty committed to providing a high-quality education to all students. The university is also home to several research centers and institutes focused on addressing important societal issues, including healthcare disparities, environmental sustainability, and urban revitalization.\n" +
            "\n" +
            "Today, Cal State LA is one of the most dynamic and innovative public universities in California, with a focus on providing a transformative education that prepares students for success in the 21st century. The university continues to expand its academic offerings, invest in its infrastructure and facilities, and engage with its local community and the broader world.", "The University of Pennsylvania (Penn) is a private Ivy League research university located in Philadelphia, Pennsylvania, USA. It was founded in 1740 by Benjamin Franklin, who was one of the Founding Fathers of the United States and an important figure in American history.\n" +
            "\n" +
            "At the time of its founding, Penn was the first university in the United States that combined both undergraduate and graduate education in the same institution, and it has been a pioneer in higher education ever since. In its early years, Penn focused on providing a practical education that was relevant to the needs of the community, and it became known for its strong programs in medicine, law, and business.\n" +
            "\n" +
            "Over time, Penn expanded its academic offerings to include a wide range of fields, and it became a leading center for research and scholarship. It has been at the forefront of many important discoveries and innovations, including the development of the first general-purpose electronic computer (ENIAC) in 1946, and the discovery of the hepatitis B virus in 1967.\n" +
            "\n" +
            "Today, Penn is one of the most prestigious universities in the world, with a distinguished faculty, a diverse student body, and a commitment to excellence in teaching, research, and service. It offers a wide range of undergraduate, graduate, and professional programs across various fields, including the humanities, social sciences, natural sciences, and engineering.\n" +
            "\n" +
            "Penn has also played an important role in shaping the city of Philadelphia and the broader world. It has been a center for social and political activism, with a long history of engagement in issues such as civil rights, environmental sustainability, and healthcare reform. It has also produced numerous notable alumni, including several U.S. presidents, Nobel laureates, and Pulitzer Prize winners."};
    private int[] images = {R.drawable.university1, R.drawable.university2, R.drawable.university3, R.drawable.university4, R.drawable.uni9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uni_stats);

        listView = findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, universities);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               String selectedUniversity = universities[position];
               String selectedDescription = descriptions[position];
               //int graphs
               int selectedImage = images[position];

               Intent intent = new Intent(UniStats.this, UniSelect.class);
               intent.putExtra("university", selectedUniversity);
               intent.putExtra("description", selectedDescription);
               intent.putExtra("image", selectedImage);
               startActivity(intent);
           }

        });



    }
}