/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.lifecycle.ViewModel

class DataViewModel : ViewModel() {
    val puppies = listOf(
        Puppy(
            id = 1,
            name = "Lilly",
            character = "Sweet Dog",
            gender = Gender.FEMALE,
            ageInMonth = 6,
            height = 1f,
            location = "Bangalore",
            color = "White",
            imageUrl = "https://di7dud5r8j0ls.cloudfront.net//puppies/images/187x140_IMG_3161(1).jpg",
            description = "The Afador is a mixed breed dog–a cross between the Afghan Hound and Labrador Retriever dog breeds. Loyal, energetic, and affectionate, these pups inherited some of the best qualities from both of their parents."
        ),
        Puppy(
            id = 2,
            name = "Lucky",
            character = "Brave dog",
            gender = Gender.FEMALE,
            ageInMonth = 8,
            height = 1.2f,
            location = "Hyderabad",
            color = "Black",
            imageUrl = "https://di7dud5r8j0ls.cloudfront.net//puppies/images/187x140_WhatsApp%20Image%202020-08-03%20at%2018.10.06.jpeg",
            description = "Canines in the Affenpinscher dog breed were originally created to be ratters in homes, stables, and shops. Bred down in size, they moved up in the world, becoming ladies’ companions. Today, they are happy, mischievous companion dogs."
        ),
        Puppy(
            id = 3,
            name = "Tommy",
            character = "Hyperactive Dog",
            gender = Gender.MALE,
            ageInMonth = 8,
            height = 0.8f,
            location = "Chennai",
            color = "Brown",
            imageUrl = "https://di7dud5r8j0ls.cloudfront.net//puppies/images/187x140_IMG_20181005_140126.jpg",
            description = "The Affenhuahua is a mixed breed dog–a cross between the Chihuahua and Affenpinscher dog breeds. Petite, sassy, and highly energetic, these pups inherited some of the best traits from both of their parents."
        ),
        Puppy(
            id = 4,
            name = "Lucy",
            character = "Naughty Dog",
            gender = Gender.FEMALE,
            ageInMonth = 10,
            height = 0.9f,
            location = "Delhi",
            color = "Light Brown",
            imageUrl = "https://di7dud5r8j0ls.cloudfront.net//puppies/images/187x140_IMG-20180827-WA0022.jpg",
            description = "The Akbash is a rare, purebred dog from the country of Turkey. Loyal, alert, and intelligent, these pups have some of the best qualities you could ask for."
        ),
        Puppy(
            id = 5,
            name = "Bruno",
            character = "Posessive Dog",
            gender = Gender.MALE,
            ageInMonth = 10,
            height = 0.9f,
            location = "Mumbai",
            color = "White",
            imageUrl = "https://di7dud5r8j0ls.cloudfront.net//fit-in/187x140/puppies/images/IMG-20200713-WA0019.jpg",
            description = "Known as the “King of Terriers,” the Airedale is indeed the largest of all terriers. The dog breed originated in the Aire Valley of Yorkshire, and was created to catch otters and rats in the region between the Aire and Wharfe Rivers. An able sporting dog, he became an ideal working dog as well, proving his worth during World War I. Intelligent, outgoing, and confident, the Airedale possesses a wonderful playful streak that delights his owners."
        ),
        Puppy(
            id = 6,
            name = "Bunty",
            character = "Innocent Dog",
            gender = Gender.MALE,
            ageInMonth = 10,
            height = 0.9f,
            location = "Kolkata",
            color = "Golden",
            imageUrl = "https://di7dud5r8j0ls.cloudfront.net//fit-in/187x140/puppies/images/IMG-20200713-WA0095.jpg",
            description = "The Afghan Hound is elegance personified. This unique, ancient dog breed has an appearance quite unlike any other: dramatic silky coat, exotic face, and thin, fashion-model build. Looks aside, Afghan enthusiasts describe this hound as both aloof and comical. Hailing from Afghanistan, where the original name for the breed was Tazi, the Afghan is thought to date back to the pre-Christian era and is considered one of oldest breeds."
        ),
        Puppy(
            id = 7,
            name = "Snoopy",
            character = "Funny Dog",
            gender = Gender.FEMALE,
            ageInMonth = 10,
            height = 0.9f,
            location = "Patna",
            color = "Brown Black",
            imageUrl = "https://di7dud5r8j0ls.cloudfront.net//fit-in/187x140/puppies/images/187x140_WhatsApp%20Image%202020-07-31%20at%2017.25.10.jpeg",
            description = "The Akita Chow is a mixed breed dog–a cross between the Akita and Chow Chow dog breeds. Large, independent, and loyal, these pups inherited some of the best traits from both of their parents."
        )
    )

    private var _clickedPuppy: Puppy? = null

    val clickedPuppy get() = _clickedPuppy ?: puppies.first()

    fun onPuppyClicked(puppy: Puppy) {
        this._clickedPuppy = puppy
    }
}
